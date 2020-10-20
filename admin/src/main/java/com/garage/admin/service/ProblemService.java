package com.garage.admin.service;

import com.garage.admin.dao.ReplyDAO;
import com.garage.admin.dao.ProblemDAO;
import com.garage.admin.model.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class ProblemService {
    @Autowired
    ProblemDAO problemDAO;

    @Autowired
    ReplyDAO replyDAO;

    public Problem getById(int id) {
        return problemDAO.getById(id);
    }

    public List<Problem> getLatestProblems(String username, int offset, int limit) {
        replyDAO.updateStatus(username);
        return problemDAO.selectLatestProblems(username,offset,limit);
    }

    public int addProblem(Problem problem) {
        //html过滤
        problem.setTitle(HtmlUtils.htmlEscape(problem.getTitle()));
        problem.setContent(HtmlUtils.htmlEscape(problem.getContent()));
        return problemDAO.addProblem(problem) > 0 ? problem.getId() : 0;
    }


    public int deleteById(int id) {
        problemDAO.deleteById(id);
        return 0;
    }
}
