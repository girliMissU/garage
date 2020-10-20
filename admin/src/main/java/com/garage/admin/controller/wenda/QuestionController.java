package com.garage.admin.controller.wenda;

import com.garage.admin.model.wenda.EntityType;
import com.garage.admin.model.Manager;
import com.garage.admin.model.wenda.Question;
import com.garage.admin.service.wenda.FollowService;
import com.garage.admin.service.ManagerService;
import com.garage.admin.service.wenda.QuestionService;
import com.garage.admin.util.GarageUtil;
import com.garage.admin.util.JedisAdapter;
import com.garage.admin.util.RedisKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/center")
public class QuestionController {
    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    QuestionService questionService;

    @Autowired
    ManagerService managerService;

    @Autowired
    JedisAdapter jedisAdapter;

    @Autowired
    FollowService followService;

    @RequestMapping(value="/get_question_by_id", method = {RequestMethod.POST})
    public Question getQuestionById(@RequestParam("questionId") int id) {
        return questionService.getById(id);
    }

    @RequestMapping(value="/get_question_PV", method = {RequestMethod.POST})
    public int getQuestionPV(@RequestParam("questionId") int questionId) {

        String PVKey = RedisKeyUtil.getPVKey(questionId);
        if(jedisAdapter.get(PVKey) == null){
            jedisAdapter.set(PVKey,"0");
        }
        int pv = Integer.parseInt(jedisAdapter.get(PVKey));
        ++pv;
        jedisAdapter.set(PVKey,String.valueOf(pv));
        return pv;
    }

    @RequestMapping(value="/get_question_list", method = {RequestMethod.POST})
    public List<Object> getQuestionList(@RequestParam("all") int all,
                                         @RequestParam("username") String username,
                                        @RequestParam("viewName") String viewName) {
        List<Question> questionList = new ArrayList<Question>();
        Manager manager = managerService.getUserByUsername(username);
        Manager viewUser = managerService.getUserByUsername(viewName);//取得要查看的主页用户
        if(all == 1){
            questionList = questionService.getLatestQuestions(0,0,15);
        }else{
            questionList = questionService.getLatestQuestions(viewUser.getId(),0,10);
        }

        List<String> pv = new ArrayList<String>();
        for (Question question : questionList) {
            String PVKey = RedisKeyUtil.getPVKey(question.getId());
            if(jedisAdapter.get(PVKey) == null){
                jedisAdapter.set(PVKey,"0");
            }
            pv.add(jedisAdapter.get(PVKey));
        }
        List<Object> resultList = new ArrayList<>();
        resultList.add(questionList);
        resultList.add(pv);
        resultList.add(viewUser);
        resultList.add(followService.getFolloweeCount(viewUser.getId(), EntityType.ENTITY_USER));
        resultList.add(followService.getFollowerCount(EntityType.ENTITY_USER, viewUser.getId()));
        resultList.add(followService.isFollower(manager.getId(),EntityType.ENTITY_USER,viewUser.getId()));
        // 1、问题详情；2、浏览量；3、用户信息；4、关注数；5、粉丝数;6、是否关注标志
        return resultList;

    }


    @RequestMapping(value = "/add_question", method = {RequestMethod.POST})
    public String addQuestion(@RequestParam("title") String title,
                              @RequestParam("content") String content,
                              @RequestParam("username") String username) {
        try {
            Manager manager = managerService.getUserByUsername(username);
            Question question = new Question();
            question.setUsername(username);
            question.setUserId(manager.getId());
            question.setContent(content);
            question.setCreatedDate(new Date());
            question.setTitle(title);
            question.setCommentCount(0);
            if (questionService.addQuestion(question) > 0) {
                return GarageUtil.getJSONString(0);
            }
        } catch (Exception e) {
            logger.error("增加问题失败" + e.getMessage());
        }
        return GarageUtil.getJSONString(1, "失败");
    }


}
