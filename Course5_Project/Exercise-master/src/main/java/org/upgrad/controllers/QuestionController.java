package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.Question;
import org.upgrad.models.User;
import org.upgrad.services.QuestionService;
import org.upgrad.services.UserService;

import javax.servlet.http.HttpSession;
import org.upgrad.repositories.QuestionRepository;
import org.upgrad.repositories.UserRepository;



@RestController
public class QuestionController {

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;


    @PostMapping("api/question")
    public ResponseEntity<?> createQuestion(@RequestParam("content") String body, HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {

            Long id = System.currentTimeMillis() % 1000;
            questionService.createQuestion (id.intValue (), body,userService.getUserID ((String) session.getAttribute("currUser")));
            return new ResponseEntity<>("Question added successfully", HttpStatus.OK);

        }
    }

    @GetMapping("/api/question/all")
    public ResponseEntity<?> getAllQuestionsByUser(HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {
            return new ResponseEntity<>(questionService.getAllQuestionsByUser(userService.getUserID ((String) session.getAttribute("currUser"))), HttpStatus.OK);
        }
    }

    @DeleteMapping("/api/question/{questionId}")
    public ResponseEntity<?> deleteQuestionByQuestionId(@RequestParam("questionId") int questionId,HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {


            int userId = questionService.findUserByQuestionId (questionId);
            if(userId == (userService.getUserID ((String) session.getAttribute("currUser")))){
                questionService.deleteQuestionById (questionId);
                return new ResponseEntity<>("Question with questionId " + questionId + " deleted successfully", HttpStatus.OK);
            }

            else{
                return new ResponseEntity<>("You do not have rights to delete this question!", HttpStatus.UNAUTHORIZED);
            }


        }
    }




}