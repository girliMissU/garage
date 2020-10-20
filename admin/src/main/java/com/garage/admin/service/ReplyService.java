package com.garage.admin.service;


import com.garage.admin.dao.ReplyDAO;
import com.garage.admin.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class ReplyService {
    @Autowired
    ReplyDAO replyDAO;

    public List<Reply> getReplyByQuestionId(int questionId) {
        return replyDAO.selectReplyByQuestionId(questionId);
    }

    public int addReply(Reply reply) {
        reply.setContent(HtmlUtils.htmlEscape(reply.getContent()));
        return replyDAO.addReply(reply) > 0 ? reply.getId() : 0;
    }

    public int getAllUnreadCount(String toName){
        return replyDAO.getReplyUnreadCountByToName(toName);
    }


}
