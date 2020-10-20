package com.garage.admin.async.handler;

import com.garage.admin.async.EventHandler;
import com.garage.admin.async.EventModel;
import com.garage.admin.async.EventType;
import com.garage.admin.model.Manager;
import com.garage.admin.model.wenda.Message;
import com.garage.admin.service.EmailService;
import com.garage.admin.service.ManagerService;
import com.garage.admin.service.wenda.MessageService;
import com.garage.admin.util.GarageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * @author LIFAN
 * 2018/11/7 15:15
 */
@Component
public class ReplyHandler implements EventHandler {
    @Autowired
    EmailService emailService;

    @Autowired
    ManagerService managerService;

    @Autowired
    MessageService messageService;

    @Override
    public void doHandle(EventModel model) {
        Message message = new Message();
        message.setFromId(GarageUtil.SYSTEM_USERID);
        message.setToId(model.getEntityOwnerId());
        message.setCreatedDate(new Date());
        Manager manager = managerService.getUser(model.getActorId());
        message.setContent("用户" + manager.getUsername() + "回复了你");

        messageService.addMessage(message);
        emailService.sendSimpleEmail("874508852@qq.com","回复通知",message.getContent());

    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.REPLY);
    }
}
