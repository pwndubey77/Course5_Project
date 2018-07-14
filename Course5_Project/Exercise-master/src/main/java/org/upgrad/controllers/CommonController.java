package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.upgrad.services.CategoryService;
import org.upgrad.services.QuestionService;

import javax.servlet.http.HttpSession;
import java.util.Set;

@RestController
public class CommonController {

    @Autowired
    QuestionService questionService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/api/questions/all")
    public ResponseEntity<?> createQuestion() {

        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);

    }
}
