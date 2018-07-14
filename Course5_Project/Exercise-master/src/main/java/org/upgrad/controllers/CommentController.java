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
    CommentRepository commentRepository;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    AnswerService answerService;


    @PostMapping("/api/comment")
    public ResponseEntity<?> giveComment(@RequestParam("comment") String commentbody, @RequestParam("answerId") int answerId, HttpSession session) {

        if (session.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {


            int userId = userService.getUserID((String) session.getAttribute("currUser"));

            String notificationMessage = ("User with userId " + userId + " has commented on your answer with answerId " + answerId);

            commentService.giveComment(commentbody, userId, answerId);

            notificationService.sendNotificationToUser(userId, notificationMessage);

            return new ResponseEntity<>("Comment to answerId " + answerId + " added successfully", HttpStatus.OK);

        }
    }

    @PutMapping("/api/comment/{commentId}")
    public ResponseEntity<?> editComment(@RequestParam("commentId") int commentId,@RequestParam("comment") String commentBody, HttpSession session) {

        String userRole = userService.getCurrentUserRole((String) session.getAttribute("currUser"));

        int userId = commentService.getUserByCommentId (commentId);


        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {

            if(userId == (userService.getUserID ((String) session.getAttribute("currUser"))) || userRole.equals ("admin")){

                commentService.editCommentByCommentId (commentId,commentBody);

                return new ResponseEntity<>("Comment with commentId " + commentId + " edited successfully", HttpStatus.OK);
            }

            else{
                return new ResponseEntity<>("You do not have rights to edit this comment!", HttpStatus.UNAUTHORIZED);
            }


        }
    }

/*
    @DeleteMapping("/api/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@RequestParam("commentId") int commentId, HttpSession session) {

        String userRole = userService.getCurrentUserRole((String) session.getAttribute("currUser"));
        int userId = answerService.findUserByAnswerId(answerId);

        if (session.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {


            if (userId == (userService.getUserID((String) session.getAttribute("currUser"))) || userRole != null) {
                commentService.deleteComment(commentId);
                return new ResponseEntity<>("comment with commentId " + commentId + " deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("You do not have rights to delete this answer!", HttpStatus.UNAUTHORIZED);
            }


        }

    }


    @GetMapping("/api/comment/all/{answerId}")
    public ResponseEntity<?> getAllCommentsByAnswer(@RequestParam("answerId") int answerId, HttpSession session) {


        if (session.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(commentService.getAllCommentsByAnswer(answerId), HttpStatus.OK);
        }
    }

    */

}