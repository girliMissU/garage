package com.garage.admin.async.handler;

import com.garage.admin.async.EventHandler;
import com.garage.admin.async.EventModel;
import com.garage.admin.async.EventType;
import com.garage.admin.model.wenda.EntityType;
import com.garage.admin.model.Manager;
import com.garage.admin.model.wenda.Message;
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
 * 2018/12/2 19:14
 */
@Component
public class FollowHandler implements EventHandler {
    @Autowired
    MessageService messageService;

    @Autowired
    ManagerService managerService;

    @Override
    public void doHandle(EventModel model) {
        Message message = new Message();
        message.setFromId(GarageUtil.SYSTEM_USERID);
        message.setToId(model.getEntityOwnerId());
        message.setCreatedDate(new Date());
        Manager user = managerService.getUser(model.getActorId());

        if (model.getEntityType() == EntityType.ENTITY_QUESTION) {
            message.setContent("用户" + user.getUsername()
                    + "关注了你的问题" + model.getEntityId());
        } else if (model.getEntityType() == EntityType.ENTITY_USER) {
            message.setContent("用户" + user.getUsername()
                    + "关注了你" + model.getActorId());
        }

        messageService.addMessage(message);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.FOLLOW);
    }
}
