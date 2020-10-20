package com.garage.admin.controller.wenda;

import com.garage.admin.model.wenda.EntityType;
import com.garage.admin.model.wenda.Feed;
import com.garage.admin.model.Manager;
import com.garage.admin.service.wenda.FeedService;
import com.garage.admin.service.wenda.FollowService;
import com.garage.admin.service.ManagerService;
import com.garage.admin.util.JedisAdapter;
import com.garage.admin.util.RedisKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LIFAN
 * 2018/12/7 17:35
 */
@RestController
@RequestMapping("/center")
public class FeedController {
    private static final Logger logger = LoggerFactory.getLogger(FeedController.class);

    @Autowired
    FeedService feedService;

    @Autowired
    FollowService followService;

    @Autowired
    ManagerService managerService;

    @Autowired
    JedisAdapter jedisAdapter;

    //获得推的feed
    @RequestMapping(value = "/pushfeeds", method = {RequestMethod.POST, RequestMethod.GET})
    private List<Feed> getPushFeeds(@RequestParam("username") String username){
        Manager localUser = managerService.getUserByUsername(username);
        List<String> feedIds = jedisAdapter.lrange(RedisKeyUtil.getTimelineKey(localUser.getId()), 0, 10);
        List<Feed> feeds = new ArrayList<Feed>();
        for (String feedId : feedIds) {
            Feed feed = feedService.getById(Integer.parseInt(feedId));
            if (feed != null) {
                feeds.add(feed);
            }
        }
        return feeds;
    }

    //获得拉的feed
    @RequestMapping(value = "/pullfeeds", method = {RequestMethod.POST, RequestMethod.GET})
    private List<Feed> getPullFeeds(@RequestParam("username") String username){
        Manager localUser = managerService.getUserByUsername(username);
        List<Integer> followees = new ArrayList<>();
        if (localUser.getId() != 0) {
            // 关注的人
            followees = followService.getFollowees(localUser.getId(), EntityType.ENTITY_USER, Integer.MAX_VALUE);
        }
        List<Feed> feeds = feedService.getUserFeeds(Integer.MAX_VALUE, followees, 10);
        return feeds;

    }
}
