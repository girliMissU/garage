package com.garage.admin.controller.wenda;

import com.garage.admin.async.EventModel;
import com.garage.admin.async.EventProducer;
import com.garage.admin.async.EventType;
import com.garage.admin.model.wenda.EntityType;
import com.garage.admin.model.Manager;
import com.garage.admin.model.wenda.Question;
import com.garage.admin.service.wenda.CommentService;
import com.garage.admin.service.wenda.FollowService;
import com.garage.admin.service.ManagerService;
import com.garage.admin.service.wenda.QuestionService;
import com.garage.admin.util.GarageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LIFAN
 * 2018/11/29 15:26
 */
@RestController
@RequestMapping("/center")
public class FollowController {
    @Autowired
    FollowService followService;

    @Autowired
    CommentService commentService;

    @Autowired
    QuestionService questionService;

    @Autowired
    ManagerService managerService;

    @Autowired
    EventProducer eventProducer;

    //关注用户
    @RequestMapping(value = "/follow_user", method = {RequestMethod.POST, RequestMethod.GET})
    public String followUser(@RequestParam("username") String username,
                             @RequestParam("userId") int userId) {
        Manager manager = managerService.getUserByUsername(username);
        boolean ret = followService.follow(manager.getId(), EntityType.ENTITY_USER, userId);

        eventProducer.fireEvent(new EventModel(EventType.FOLLOW)
                .setActorId(manager.getId()).setEntityId(userId)
                .setEntityType(EntityType.ENTITY_USER).setEntityOwnerId(userId));

        // 返回关注的人数
        return GarageUtil.getJSONString(ret ? 0 : 1, String.valueOf(followService.getFolloweeCount(manager.getId(), EntityType.ENTITY_USER)));
    }


    //取消关注用户
    @RequestMapping(value = "/un_follow_user", method = {RequestMethod.POST})
    public String unFollowUser(@RequestParam("username") String username,
                               @RequestParam("userId") int userId) {
        Manager manager = managerService.getUserByUsername(username);
        boolean ret = followService.unfollow(manager.getId(), EntityType.ENTITY_USER, userId);

        eventProducer.fireEvent(new EventModel(EventType.UNFOLLOW)
                .setActorId(manager.getId()).setEntityId(userId)
                .setEntityType(EntityType.ENTITY_USER).setEntityOwnerId(userId));

        // 返回关注的人数
        return GarageUtil.getJSONString(ret ? 0 : 1, String.valueOf(followService.getFolloweeCount(manager.getId(), EntityType.ENTITY_USER)));
    }

    //关注问题
    @RequestMapping(value = "/follow_question", method = {RequestMethod.POST})
    public String FollowQuestion(@RequestParam("username") String username,
                                 @RequestParam("questionId") int questionId) {
        Manager manager = managerService.getUserByUsername(username);
        Question q = questionService.getById(questionId);
        if (q == null) {
            return GarageUtil.getJSONString(1, "问题不存在");
        }

        boolean ret = followService.follow(manager.getId(), EntityType.ENTITY_QUESTION, questionId);

        eventProducer.fireEvent(new EventModel(EventType.FOLLOW)
                .setActorId(manager.getId()).setEntityId(questionId)
                .setEntityType(EntityType.ENTITY_QUESTION).setEntityOwnerId(q.getUserId()));

        Map<String, Object> info = new HashMap<>();
        info.put("name", manager.getUsername());
        info.put("id", manager.getId());
        info.put("count", followService.getFollowerCount(EntityType.ENTITY_QUESTION, questionId));
        return GarageUtil.getJSONString(ret ? 0 : 1, info);

    }

    //取消关注问题
    @RequestMapping(value = "/un_follow_question", method = {RequestMethod.POST})
    public String UFollowQuestion(@RequestParam("username") String username,
                                  @RequestParam("questionId") int questionId) {
        Manager manager = managerService.getUserByUsername(username);
        Question q = questionService.getById(questionId);
        if (q == null) {
            return GarageUtil.getJSONString(1, "问题不存在");
        }

        boolean ret = followService.unfollow(manager.getId(), EntityType.ENTITY_QUESTION, questionId);

        eventProducer.fireEvent(new EventModel(EventType.UNFOLLOW)
                .setActorId(manager.getId()).setEntityId(questionId)
                .setEntityType(EntityType.ENTITY_QUESTION).setEntityOwnerId(q.getUserId()));

        Map<String, Object> info = new HashMap<>();
        info.put("id", manager.getId());
        info.put("count", followService.getFollowerCount(EntityType.ENTITY_QUESTION, questionId));
        return GarageUtil.getJSONString(ret ? 0 : 1, info);

    }

    //获得粉丝列表
    @RequestMapping(value = "/followers", method = {RequestMethod.GET})
    public String followers(@RequestParam("username") String username) {
        Manager manager = managerService.getUserByUsername(username);
        List<Integer> followerIds = followService.getFollowers(EntityType.ENTITY_USER, manager.getId(), 0, 10);
        return GarageUtil.getJSONString(0,getUsersInfo(manager.getId(), followerIds));
    }

    //获得关注列表
    @RequestMapping(value = "/followees", method = {RequestMethod.GET})
    public String followes(@RequestParam("username") String username) {
        Manager manager = managerService.getUserByUsername(username);
        List<Integer> followeeIds = followService.getFollowees(manager.getId(), EntityType.ENTITY_USER, 0, 10);
        return GarageUtil.getJSONString(0,getUsersInfo(manager.getId(), followeeIds));
    }

    private Map<String, Object> getUsersInfo(int localUserId, List<Integer> userIds) {
        List<Manager> userList = new ArrayList<Manager>();
        List<Object> followerCountList = new ArrayList<Object>();
        List<Object> followeeCountList = new ArrayList<Object>();
        List<Object> ifFollowList = new ArrayList<Object>();
        for (Integer uid : userIds) {
            Manager user = managerService.getUser(uid);
            if (user == null) {
                continue;
            }
            userList.add(user);
            followerCountList.add(followService.getFollowerCount(EntityType.ENTITY_USER, uid));
            followeeCountList.add(followService.getFolloweeCount(uid, EntityType.ENTITY_USER));
            ifFollowList.add(followService.isFollower(localUserId,EntityType.ENTITY_USER,uid));
        }
        Map<String, Object> info = new HashMap<>();
        info.put("user", userList);
        info.put("followerCount", followerCountList);
        info.put("followeeCount", followeeCountList);
        info.put("ifFollow", ifFollowList);
        return info;
    }

}

