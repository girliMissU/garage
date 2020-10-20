package com.garage.admin.service.wenda;

import com.garage.admin.dao.wenda.MessageDAO;
import com.garage.admin.model.wenda.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageDAO messageDAO;

    public int addMessage(Message message) {
        return messageDAO.addMessage(message) > 0 ? message.getId() : 0;
    }

    public int updateHasRead(int id) {
        return messageDAO.updateHasRead(id);
    }

    public List<Message> getConversationDetail(String conversationId, int offset, int limit) {
        return  messageDAO.getConversationDetail(conversationId, offset, limit);
    }

    public List<Message> getConversationList(int userId, int offset, int limit) {
        return  messageDAO.getConversationList(userId, offset, limit);
    }

    public int getConversationUnreadCount(int userId, String conversationId) {
        return messageDAO.getConversationUnreadCount(userId, conversationId);
    }

    public int getManagerUnreadCount(int userId) {
        return messageDAO.getManagerUnreadCount(userId);
    }
}
