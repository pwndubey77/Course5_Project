package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.repositories.CommentRepository;
import org.upgrad.services.AnswerService;
import org.upgrad.services.CommentService;
import org.upgrad.services.NotificationService;
import org.upgrad.services.UserService;

import javax.servlet.http.HttpSession;


@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    AnswerService answerService;


    /*
     * API - giveComment
     *
     * @parameter "comment" and "answerId"is provided by user and,session (HttpSession ) gives details of session
     *
     * -- Session details are checked for authentication - if fails -> Unauthorized - goes to return httpStatus,
     *                                                       - if pass -> call respective services ,
     *
     * -- commentService is called to add entry with user,comment and answerId to add entries in database
     *
     * -- notification Services called to update notification for user who posted the answer
     *
     * -- return response body + HttpStatus
     *
     */
    @PostMapping("/api/comment")
    public ResponseEntity<?> giveComment(@RequestParam("comment") String commentBody, @RequestParam("answerId") int answerId, HttpSession session) {

        if (session.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {


            int userId = userService.getUserID((String) session.getAttribute("currUser"));

            String notificationMessage = ("User with userId " + userId + " has commented on your answer with answerId " + answerId);

            commentService.giveComment(commentBody, userId, answerId);

            notificationService.sendNotificationToUser(userId, notificationMessage);

            return new ResponseEntity<>("Comment to answerId " + answerId + " added successfully", HttpStatus.OK);

        }
    }

    /*
     * API - editComment
     *
     * @parameter "commentId" and "commentBody"is provided by user and,session (HttpSession ) gives details of session
     *
     * -- Session details are checked for authentication - if fails -> Unauthorized - goes to return httpStatus,
     *                                                   - if pass -> call user service to authenticate user is admin/user (who added the comment) ,
     *
     * -- answerService called to modify the  entry with commentId , commentBody to add update in database
     *
     * -- return response body + HttpStatus
     *
     */
    @PutMapping("/api/comment/{commentId}")
    public ResponseEntity<?> editComment(@RequestParam("commentId") int commentId,@RequestParam("comment") String commentBody, HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {

            String userRole = userService.getCurrentUserRole((String) session.getAttribute ("currUser"));

            int userId = userService.getUserID((String) session.getAttribute("currUser"));

            if(userId == (commentService.getUserByCommentId (commentId)) || userRole.equalsIgnoreCase ("admin")){

                commentService.editCommentByCommentId (commentId,commentBody);

                return new ResponseEntity<>("Comment with commentId " + commentId + " edited successfully", HttpStatus.OK);
            }

            else{
                return new ResponseEntity<>("You do not have rights to edit this comment!", HttpStatus.FORBIDDEN);
            }


        }
    }


    /*
     * API - deleteComment
     *
     * @parameter "commentId" and "commentBody"is provided by user and,session (HttpSession ) gives details of session
     *
     * -- Session details are checked for authentication - if fails -> Unauthorized - goes to return httpStatus,
     *                                                   - if pass -> call user service to authenticate user is admin/user (who added the comment) ,
     *
     * -- answerService called to modify the  entry with commentId , commentBody to add update in database
     *
     * -- return response body + HttpStatus
     *
     */

    @DeleteMapping("/api/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@RequestParam("commentId") int commentId, HttpSession session) {

        if (session.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {

            String userRole = userService.getCurrentUserRole((String) session.getAttribute("currUser"));
            int userId = commentService.getUserByCommentId(commentId);

            if (userId == (userService.getUserID((String) session.getAttribute("currUser"))) || userRole.equalsIgnoreCase ("admin")) {
                commentService.deleteCommentByCommentId(commentId);
                return new ResponseEntity<>("comment with commentId " + commentId + " deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("You do not have rights to delete this comment!", HttpStatus.FORBIDDEN);
            }

        }

    }

    /*
     * API - getAllCommentsByAnswer
     *
     * @parameter "answerId" is provided by user and,session (HttpSession ) gives details of session
     *
     * -- Session details are checked for authentication - if fails -> Unauthorized - goes to return httpStatus,
     *                                                   - if pass -> call respective services
     *
     * -- answerService called to modify the  entry with commentId , commentBody to add update in database
     *
     * -- return response body + HttpStatus
     *
     */
    @GetMapping("/api/comment/all/{answerId}")
    public ResponseEntity<?> getAllCommentsByAnswer(@RequestParam("answerId") int answerId, HttpSession session) {


        if (session.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(commentService.getAllCommentsByAnswerId(answerId), HttpStatus.OK);
        }
    }


}