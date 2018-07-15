package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.upgrad.repositories.LikeRepository;
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



            int checkForUserLike = likeService.checkForUserInLikedByList(currentUser,answerId);
            if(checkForUserLike > 0){

                return new ResponseEntity<>("You have already liked this answer!", HttpStatus.OK);
            }
            else{

                likeService.addLikesByUserForAnswerId(currentUser,answerId);

                notificationService.sendNotificationToUser(currentUser, notificationMessage);

                return new ResponseEntity<>("answerId " + answerId + " liked successfully", HttpStatus.OK);

            }


        }
    }


    @DeleteMapping("/api/unlike/{answerId}")
    public ResponseEntity<?> unlike (@RequestParam("answerId") int answerId, HttpSession session){

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {


            int currentUser = userService.getUserID ((String) session.getAttribute ("currUser"));
            int checkForUserLike = likeService.checkForUserInLikedByList(currentUser,answerId);
            if(checkForUserLike > 0){

                likeService.unlikeAnswer(currentUser,answerId);
                return new ResponseEntity<>("You have unliked answer with answerId " +answerId+ " successfully", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("You have not liked this answer", HttpStatus.NOT_FOUND);
            }

        }
    }

    //problem in query get user entry...
    @PostMapping("/api/follow/{categoryId}")
    public ResponseEntity<?> addFollowCategory (@RequestParam("categoryId") int categoryId, HttpSession session){

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {


            int currentUser = userService.getUserID ((String) session.getAttribute ("currUser"));
            int checkForUserFollow = followService.checkForUserInFollowedByList(currentUser);
            if(checkForUserFollow > 0){

                return new ResponseEntity<>("You have already followed this category!", HttpStatus.UNAUTHORIZED);
          }
            else{

                followService.followCategory(currentUser,categoryId);
                return new ResponseEntity<>("category "+categoryId+" followed successfully", HttpStatus.OK);
            }

        }
    }

    //problem in query get user entry...
    @DeleteMapping("/api/unfollow/{categoryId}")
    public ResponseEntity<?> unFollow (@RequestParam("categoryId") int categoryId, HttpSession session){
        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {


            int currentUser = userService.getUserID ((String) session.getAttribute ("currUser"));
            int checkForFollowEntry = followService.checkForUserInFollowedByList(currentUser);
            if(checkForFollowEntry > 0){

                followService.unfollowCategory(checkForFollowEntry);
                return new ResponseEntity<>("You have unfollowed the category " +categoryId+ " successfully", HttpStatus.OK);

            }
            else{
                return new ResponseEntity<>("You are curently not following this category", HttpStatus.UNAUTHORIZED);
            }

        }
    }

}
