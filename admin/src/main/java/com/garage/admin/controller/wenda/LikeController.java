package com.garage.admin.controller.wenda;

import com.garage.admin.async.EventModel;
import com.garage.admin.async.EventProducer;
import com.garage.admin.async.EventType;
import com.garage.admin.model.wenda.Comment;
import com.garage.admin.model.wenda.EntityType;
import com.garage.admin.model.Manager;
import com.garage.admin.service.wenda.CommentService;
import com.garage.admin.service.wenda.LikeService;
import com.garage.admin.service.ManagerService;
import com.garage.admin.util.GarageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/center")
public class LikeController {
    @Autowired
    LikeService likeService;

    @Autowired
    ManagerService managerService;

    @Autowired
    CommentService commentService;

    @Autowired
    EventProducer eventProducer;

    @RequestMapping(path = {"/like"}, method = {RequestMethod.POST})
    public String like(@RequestParam("username") String username,
                       @RequestParam("commentId") int commentId) {
        Manager manager = managerService.getUserByUsername(username);

        Comment comment = commentService.getCommentById(commentId);

        eventProducer.fireEvent(new EventModel(EventType.LIKE)
                .setActorId(manager.getId()).setEntityId(commentId)
                .setEntityType(EntityType.ENTITY_COMMENT)
                .setEntityOwnerId(comment.getUserId()));


        long likeCount = likeService.like(manager.getId(), EntityType.ENTITY_COMMENT, commentId);
        return GarageUtil.getJSONString(0, String.valueOf(likeCount));
    }

    @RequestMapping(path = {"/dislike"}, method = {RequestMethod.POST})
    public String dislike(@RequestParam("username") String username,
                          @RequestParam("commentId") int commentId) {
        Manager manager = managerService.getUserByUsername(username);
        long likeCount = likeService.disLike(manager.getId(), EntityType.ENTITY_COMMENT, commentId);
        return GarageUtil.getJSONString(0,String.valueOf(likeCount));
    }



}
