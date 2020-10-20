package com.garage.admin.controller.wenda;

import com.garage.admin.model.Manager;
import com.garage.admin.model.wenda.Message;
import com.garage.admin.model.ResultInfo;
import com.garage.admin.service.ManagerService;
import com.garage.admin.service.wenda.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author LIFAN
 * 2018/10/15 19:36
 */
@RestController
@RequestMapping("/center")
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    MessageService messageService;

    @Autowired
    ManagerService managerService;

    @RequestMapping(value="/add_message", method = {RequestMethod.POST})
    public ResultInfo addProblem(@RequestParam("username") String username,
                             @RequestParam("toName") String toName,
                             @RequestParam("content") String content) {
        try {
            Manager fromManager = managerService.getUserByUsername(username);
            Manager toManager = managerService.getUserByUsername(toName);
            int fromId = fromManager.getId();
            int toId = toManager.getId();

            if (toManager == null) {
                return ResultInfo.build(0, "用户不存在");
            }
            Message msg = new Message();
            msg.setContent(content);
            msg.setFromId(fromId);
            msg.setToId(toId);
            msg.setCreatedDate(new Date());
            msg.setConversationId(fromId < toId ? String.format("%d_%d", fromId, toId) : String.format("%d_%d", toId, fromId));
            messageService.addMessage(msg);
            return ResultInfo.build(1,"添加站内信成功");
        } catch (Exception e) {
            logger.error("增加站内信失败" + e.getMessage());
            return ResultInfo.build(-1, "增加站内信失败");
        }

    }

    @RequestMapping(value="/message_list", method = {RequestMethod.POST})
    public ResultInfo messageList(@RequestParam("username") String username) {
        try {
            Manager manager = managerService.getUserByUsername(username);
            List<Object> conversations = new ArrayList<>();
            List<Integer> unRead = new ArrayList<Integer>();
            List<Manager> targetManagers = new ArrayList<Manager>();
            List<Message> conversationList = messageService.getConversationList(manager.getId(), 0, 10);
            conversations.add(conversationList);
            for (Message msg : conversationList) {
                int targetId = msg.getFromId() == manager.getId() ? msg.getToId() : msg.getFromId();
                Manager targetManager = managerService.getUser(targetId);
                targetManagers.add(targetManager);
                int unread =  messageService.getConversationUnreadCount(manager.getId(), msg.getConversationId());
                unRead.add(unread);
            }
            conversations.add(targetManagers);
            conversations.add(unRead);
            return ResultInfo.ok(conversations);
        } catch (Exception e) {
            logger.error("获取站内信列表失败" + e.getMessage());
            return ResultInfo.build(-1, "获取站内信列表失败");
        }
    }

    @RequestMapping(value="/unread_count", method = {RequestMethod.POST})
    public ResultInfo unreadCount(@RequestParam("username") String username) {
        try {
            Manager manager = managerService.getUserByUsername(username);
            return ResultInfo.ok(messageService.getManagerUnreadCount(manager.getId()));
        } catch (Exception e) {
            logger.error("获取未阅读消息失败" + e.getMessage());
            return ResultInfo.build(-1,"获取未阅读消息失败");
        }
    }

    @RequestMapping(value="/message_detail", method = {RequestMethod.POST})
    public ResultInfo messageDetail(@RequestParam("conversationId") String conversationId) {
        try {
            //取得该对话ID对应的所有对话消息
            List<Message> conversationList = messageService.getConversationDetail(conversationId, 0, 20);
            List<Object> messages = new ArrayList<>();
            messages.add(conversationList);

            //取得发消息人的信息
            List<Manager> fromManagers = new ArrayList<Manager>();
            for (Message msg : conversationList) {
                //更新未阅读的消息
                messageService.updateHasRead(msg.getId());
                //取得发消息人的信息
                Manager fromManager = managerService.getUser(msg.getFromId());
                fromManagers.add(fromManager);
            }
            messages.add(fromManagers);
            return ResultInfo.ok(messages);
        } catch (Exception e) {
            logger.error("获取详情消息失败" + e.getMessage());
            return null;
        }
    }
}
