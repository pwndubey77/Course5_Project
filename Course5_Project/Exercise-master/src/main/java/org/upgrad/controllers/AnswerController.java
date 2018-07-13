package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.User;
import org.upgrad.services.AnswerService;
import org.upgrad.services.QuestionService;
import org.upgrad.services.UserService;

import javax.servlet.http.HttpSession;

@RestController
public class AnswerController {

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;


    @PostMapping("api/answer")
    public ResponseEntity<?> createQuestion(@RequestParam("answer") String answerBody,@RequestParam("questionId") int questionId,HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {


            answerService.addAnswer (answerBody,questionService.findUserByQuestionId (questionId),questionId);

            //notifcation to user who asked question
            //notificationservices- message (question answerwed by USER - CurrUser-details ....)
            return new ResponseEntity<>("Answer to questionId "+questionId + " added successfully", HttpStatus.OK);

        }
    }

    //problem in query - update query
    @PostMapping("/api/answer/{answerId}")
    public ResponseEntity<?> editAnswer(@RequestParam("answer") String answerBody,@RequestParam("answerId") int answerId,HttpSession session) {


        String userRole = userService.getCurrentUserRole((String) session.getAttribute("currUser"));
        int userId = answerService.findUserByAnswerId (answerId);

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {

            if(userId == (userService.getUserID ((String) session.getAttribute("currUser"))) || userRole!=null){

                answerService.editAnswerByAnswerId (answerId,answerBody);
                return new ResponseEntity<>("Answer with answerId "+answerId + " edited successfully", HttpStatus.OK);
            }

            else{
                return new ResponseEntity<>("You do not have rights to edit this answer!", HttpStatus.UNAUTHORIZED);
            }


        }
    }

    @GetMapping("/api/answer/all/{questionId}")
    public ResponseEntity<?> getAllAnswersToQuestion(@RequestParam("questionId") int questionId,HttpSession session) {


        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {
            return new ResponseEntity<>(answerService.getAllAnswersByQuestionId(questionId), HttpStatus.OK);
        }
    }

    @GetMapping("/api/answer/all")
    public ResponseEntity<?> getAllAnswersByUser(HttpSession session) {


        int currentUser = userService.getUserID ((String) session.getAttribute ("currUser"));
        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {
            return new ResponseEntity<>(answerService.getAllAnswersByUser(currentUser), HttpStatus.OK);
        }
    }




}
