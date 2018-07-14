package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.upgrad.services.FollowService;
import org.upgrad.services.LikeService;
import org.upgrad.services.NotificationService;
import org.upgrad.services.UserService;

import javax.servlet.http.HttpSession;

@RestController
public class LikesFollowController {

    @Autowired
    UserService userService;

    @Autowired
    LikeService likeService;

    @Autowired
    FollowService followService;

    @Autowired
    NotificationService notificationService;

    @PostMapping("/api/like/{answerId}")
    public ResponseEntity<?> giveLikes (@RequestParam("answerId") int answerId, HttpSession session){


        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {

            int currentUser = userService.getUserID ((String) session.getAttribute ("currUser"));
            String notificationMessage = ("User with userId " + currentUser + " has liked your answer with answerId " + answerId);

            likeService.addLikesByUserForAnswerId(currentUser,answerId);
            notificationService.sendNotificationToUser(currentUser, notificationMessage);

            return new ResponseEntity<>("answerId " + answerId + " liked successfully", HttpStatus.OK);
        }
    }
/*
    @DeleteMapping("/api/unlike/{answerId}")
    public ResponseEntity<?> unlike (@RequestParam("answerId") int answerId, HttpSession session){
        int currentUser = userService.getUserID ((String) session.getAttribute ("currUser"));
        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(likeService.getAllAnswersByUser(currentUser), HttpStatus.OK);
        }
    }


    @PostMapping("/api/follow/{categoryId}")
    public ResponseEntity<?> addFollowCategory (@RequestParam("categoryId") int categoryId, HttpSession session){
        int currentUser = userService.getUserID ((String) session.getAttribute ("currUser"));
        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(followService.getAllAnswersByUser(currentUser), HttpStatus.OK);
        }
    }

    @DeleteMapping("/api/unfollow/{categoryId}")
    public ResponseEntity<?> unFollow (@RequestParam("categoryId") int categoryId, HttpSession session){
        int currentUser = userService.getUserID ((String) session.getAttribute ("currUser"));
        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(followService.getAllAnswersByUser(currentUser), HttpStatus.OK);
        }
    }
    */
}
