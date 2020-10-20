package com.garage.admin.controller;

import com.garage.admin.model.Problem;
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
public class ProblemController {
    private static final Logger logger = LoggerFactory.getLogger(ProblemController.class);

    @Autowired
    ProblemService problemService;

    @Autowired
    ManagerService managerService;

    @RequestMapping(value="/add_problem", method = {RequestMethod.POST})
    public String addProblem(@RequestParam("username") String username,
                              @RequestParam("garage_id") int garageId,
                              @RequestParam("title") String title,
                              @RequestParam("content") String content) {
        try {
            Problem problem = new Problem();
            problem.setGarageId(garageId);
            problem.setContent(content);
            problem.setCreatedDate(new Date());
            problem.setTitle(title);
            problem.setUsername(username);
            problem.setStatus("已受理");
            if (problemService.addProblem(problem) > 0) {
                return GarageUtil.getJSONString(0);
            }
        } catch (Exception e) {
            logger.error("增加问题失败" + e.getMessage());
        }
        return GarageUtil.getJSONString(1, "失败");
    }


    @RequestMapping(value="/get_problems", method = {RequestMethod.POST})
    public List<Problem> getProblems(@RequestParam("username") String username) {
        return problemService.getLatestProblems(username,0,10);
    }


    @RequestMapping(value="/get_problem_by_id", method = {RequestMethod.POST})
    public Problem getProblemsById(@RequestParam("id") int id) {
        return problemService.getById(id);
    }

    @RequestMapping(value="/delete_problem_by_id", method = {RequestMethod.POST})
    public String deleteProblemById(@RequestParam("id") int id) {
        if(problemService.deleteById(id) == 0){
            return GarageUtil.getJSONString(0,"成功");
        }
        return GarageUtil.getJSONString(1,"失败");
    }
}
