package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<?> getAllQuestions(HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {
            return new ResponseEntity<>(questionService.findAllByUserId (userService.getUserID ((String) session.getAttribute("currUser"))), HttpStatus.OK);
        }
    }




}