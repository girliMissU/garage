package com.garage.admin.async.handler;

import com.garage.admin.async.EventHandler;
import com.garage.admin.async.EventModel;
import com.garage.admin.async.EventType;

import java.util.Arrays;
import java.util.List;

/**
 * @author LIFAN
 * 2019/4/9 16:20
 */
public class CommentHandler implements EventHandler {

    @Override
    public void doHandle(EventModel model) {

    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.COMMENT);
    }
}
