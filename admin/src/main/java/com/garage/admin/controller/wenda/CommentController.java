package com.garage.admin.controller.wenda;


import com.garage.admin.async.EventModel;
import com.garage.admin.async.EventProducer;
import com.garage.admin.async.EventType;
import com.garage.admin.model.*;
import com.garage.admin.model.wenda.Comment;
import com.garage.admin.model.wenda.EntityType;
import com.garage.admin.model.wenda.Question;
import com.garage.admin.service.wenda.CommentService;
import com.garage.admin.service.wenda.LikeService;
import com.garage.admin.service.ManagerService;
import com.garage.admin.service.wenda.QuestionService;
import com.garage.admin.util.GarageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/center")
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    CommentService commentService;

    @Autowired
    QuestionService questionService;

    @Autowired
    ManagerService managerService;

    @Autowired
    LikeService likeService;

    @Autowired
    EventProducer eventProducer;

    @RequestMapping(path = {"/add_comment"}, method = {RequestMethod.POST})
    public String addComment(@RequestParam("username") String username,
                             @RequestParam("questionId") int questionId,
                             @RequestParam("content") String content) {
        try {
            Manager manager = managerService.getUserByUsername(username);
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setUserId(manager.getId());
            comment.setUsername(username);
            comment.setCreatedDate(new Date());
            comment.setEntityType(EntityType.ENTITY_QUESTION);
            comment.setEntityId(questionId);
            int count = commentService.getCommentCount(comment.getEntityId(), comment.getEntityType());
            questionService.updateCommentCount(comment.getEntityId(), count);

            eventProducer.fireEvent(new EventModel(EventType.COMMENT).setActorId(comment.getUserId())
                    .setEntityId(questionId));

            if (commentService.addComment(comment) > 0) {
                return GarageUtil.getJSONString(0);
            }
        } catch (Exception e) {
            logger.error("增加评论失败" + e.getMessage());
        }
        return GarageUtil.getJSONString(1, "失败");
    }


    @RequestMapping(path = {"/get_comment_detail"}, method = {RequestMethod.POST})
    public ResultInfo getCommentDetail(@RequestParam("username") String username,
                                    @RequestParam("questionId") int questionId) {

        Question question = questionService.getById(questionId);
        Manager manager = managerService.getUserByUsername(username);
        List<Comment> commentList = commentService.getCommentsByEntity(questionId, EntityType.ENTITY_QUESTION);
        List<Object> result = new ArrayList<>();
        List<String> status = new ArrayList<String>();
        List<String> count = new ArrayList<String>();
        for (Comment comment : commentList) {
            status.add(String.valueOf(likeService.getLikeStatus(manager.getId(),EntityType.ENTITY_COMMENT, comment.getId())));
            count.add(String.valueOf(likeService.getLikeCount(EntityType.ENTITY_COMMENT, comment.getId())));
        }
        result.add(status);
        result.add(count);
        result.add(commentList);
        result.add(question);
        return ResultInfo.ok(result);
    }

}
