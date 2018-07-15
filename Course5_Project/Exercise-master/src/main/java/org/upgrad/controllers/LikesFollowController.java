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

    /*
     * API - giveLikes
     *
     * @parameter "answerId" is provided by user and,session (HttpSession ) gives details of session
     *
     * -- Session details are checked for authentication - if fails -> Unauthorized - goes to return httpStatus,
     *                                                   - if pass -> call user service for user details ,
     *
     * -- likeSrvice called to check if liked or not - if yes - return HttpStatus.forbidden (already liked)
     *
     * -- if not liked before - likeService adds entry in database -> add like
     *
     * -- return response body + HttpStatus
     *
     */
    @PostMapping("/api/like/{answerId}")
    public ResponseEntity<?> giveLikes (@RequestParam("answerId") int answerId, HttpSession session){


        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {

            int currentUser = userService.getUserID ((String) session.getAttribute ("currUser"));
            String notificationMessage = ("User with userId " + currentUser + " has liked your answer with answerId " + answerId);



            int checkForUserLike = likeService.checkForUserInLikedByList(currentUser,answerId);
            if(checkForUserLike > 0){

                return new ResponseEntity<>("You have already liked this answer!", HttpStatus.FORBIDDEN);
            }
            else{

                likeService.addLikesByUserForAnswerId(currentUser,answerId);

                notificationService.sendNotificationToUser(currentUser, notificationMessage);

                return new ResponseEntity<>("answerId " + answerId + " liked successfully", HttpStatus.CREATED);

            }


        }
    }


    /*
     * API - unlike
     *
     * @parameter "answerId" is provided by user and,session (HttpSession ) gives details of session
     *
     * -- Session details are checked for authentication - if fails -> Unauthorized - goes to return httpStatus,
     *                                                   - if pass -> call user service for user details ,
     *
     * -- likeService called to check if liked or not - if not - return httpstatus.forbidded,
     *
     * -- if like exists - like service deletes entry from database -> unlike
     *
     * -- return response body + HttpStatus
     *
     */
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
                return new ResponseEntity<>("You have not liked this answer", HttpStatus.CREATED);
            }

        }
    }


    /*
     * API - addFollowCategory
     *
     * @parameter "categoryId" is provided by user and,session (HttpSession ) gives details of session
     *
     * -- Session details are checked for authentication - if fails -> Unauthorized - goes to return httpStatus,
     *                                                   - if pass -> call user service for user details ,
     *
     * -- followService called to check if followed or not - if yes - return HttpStatus.forbidden (already followed)
     *
     * -- if not followed before - likeService adds entry in database -> followCategory
     *
     * -- return response body + HttpStatus
     *
     */
    @PostMapping("/api/follow/{categoryId}")
    public ResponseEntity<?> addFollowCategory (@RequestParam("categoryId") int categoryId, HttpSession session){

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {


            int currentUser = userService.getUserID ((String) session.getAttribute ("currUser"));
            int checkForUserFollow = followService.checkForUserInFollowedByList(currentUser,categoryId);
            if(checkForUserFollow > 0){

                return new ResponseEntity<>("You have already followed this category!", HttpStatus.FORBIDDEN);
          }
            else{

                followService.followCategory(currentUser,categoryId);
                return new ResponseEntity<>("category "+categoryId+" followed successfully", HttpStatus.CREATED);
            }

        }
    }


    /*
     * API - unFollow
     *
     * @parameter "categoryId" is provided by user and,session (HttpSession ) gives details of session
     *
     * -- Session details are checked for authentication - if fails -> Unauthorized - goes to return httpStatus,
     *                                                   - if pass -> call user service for user details ,
     *
     * -- followservice called to check if liked or not - if not - return httpstatus.forbidded,
     *
     * -- if follow exists - follow service deletes entry from database -> unFollow
     *
     * -- return response body + HttpStatus
     *
     */
    @DeleteMapping("/api/unfollow/{categoryId}")
    public ResponseEntity<?> unFollow (@RequestParam("categoryId") int categoryId, HttpSession session){
        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {


            int currentUser = userService.getUserID ((String) session.getAttribute ("currUser"));
            int checkForFollowEntry = followService.checkForUserInFollowedByList(currentUser,categoryId);
            if(checkForFollowEntry > 0){

                followService.unFollowCategory(currentUser,categoryId);
                return new ResponseEntity<>("You have unfollowed the category " +categoryId+ " successfully", HttpStatus.OK);

            }
            else{
                return new ResponseEntity<>("You are curently not following this category", HttpStatus.FORBIDDEN);
            }

        }
    }

}
