package com.garage.admin.controller;

import com.garage.admin.async.EventModel;
import com.garage.admin.async.EventProducer;
import com.garage.admin.async.EventType;
import com.garage.admin.model.wenda.EntityType;
import com.garage.admin.model.Problem;
import com.garage.admin.model.Reply;
import com.garage.admin.model.Manager;
import com.garage.admin.service.ReplyService;
import com.garage.admin.service.ProblemService;
import com.garage.admin.service.ManagerService;
import com.garage.admin.util.GarageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class ReplyController {
    private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

    @Autowired
    private ReplyService replyService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    EventProducer eventProducer;

    @RequestMapping(value="/get_reply_by_questionId", method = {RequestMethod.POST})
    public List<Reply> getReply(@RequestParam("questionId") int questionId) {
        return replyService.getReplyByQuestionId(questionId);
    }

    @RequestMapping(value="/add_reply", method = {RequestMethod.POST})
    public String addReply(@RequestParam("manager") String manager,
                           @RequestParam("questionId") int questionId,
                           @RequestParam("content") String content) {
        try{
            Problem problem = problemService.getById(questionId);
            Manager problemUser = managerService.getUserByUsername(problem.getUsername());
            Manager user = managerService.getUserByUsername(manager);
            Reply reply = new Reply();
            reply.setManager(manager);
            reply.setAuthority(user.getAuthority());
            reply.setToName((problem.getUsername().equals(manager) ? "admin" : problem.getUsername()));
            reply.setQuestionId(questionId);
            reply.setContent(content);
            reply.setCreatedDate(new Date());
            reply.setStatus(0);

            if(replyService.addReply(reply) > 0) {
                eventProducer.fireEvent(new EventModel(EventType.REPLY)
                        .setActorId(user.getId()).setEntityId(questionId)
                        .setEntityType(EntityType.ENTITY_QUESTION)
                        .setEntityOwnerId(problemUser.getId()));
                return GarageUtil.getJSONString(0);
            }
        }catch (Exception e) {
            logger.error("增加回复失败" + e.getMessage());
        }
        return GarageUtil.getJSONString(1, "失败");

    }

    @RequestMapping(value="/get_unread_count_all", method = {RequestMethod.POST})
    public int getUnreadCountAll(@RequestParam("toName") String toName) {
        return replyService.getAllUnreadCount(toName);
    }
}
