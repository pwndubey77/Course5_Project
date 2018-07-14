package org.upgrad.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.Comment;
import org.upgrad.repositories.CommentRepository;
import org.upgrad.services.CommentService;
import org.upgrad.services.UserService;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentService commentService;


    @PostMapping("/api/comment")
    public ResponseEntity<?> giveComment(@RequestParam("comment") String commentbody, @RequestParam("commentId") int commentId, HttpSession session) {

        if (session.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {


            int userId = userService.getUserID((String) session.getAttribute("currUser"));

            String notificationMessage = ("User with userId " + userId + " has commented your answer with answerId " + answerId);

            commentService.giveComment(commentId, commentbody, userId);

            notificationService.sendNotificationToUser(userId, notificationMessage);

            return new ResponseEntity<>("Comment to answerId " + answerId + " added successfully", HttpStatus.OK);

        }
    }

    @GetMapping("/api/comment/{commentId}")
    public ResponseEntity<?> editComment(@RequestParam("commentId") int commentId, HttpSession session) {

        String userRole = userService.getCurrentUserRole((String) session.getAttribute("currUser"));
        int userId = commentService.findUserbyanswerId(answerId);

        if (session.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else if (session.getAttribute("currUser") == Admin) {
            return new ResponseEntity<>("Comment with commentId" + commentId + "edited successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You do not have rights to delete this answer!", HttpStatus.UNAUTHORIZED);
        }
    }

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
}