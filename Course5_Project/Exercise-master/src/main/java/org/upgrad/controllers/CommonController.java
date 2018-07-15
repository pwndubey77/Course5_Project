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

    /*
     * API - getAllCategories
     *
     *    public API - No Authentication needed
     *
     *    categoryService is called to get all categories available with details
     *
     *    HttPResponse is returned with category lists
     *
     */
    @GetMapping("/api/categories/all")
    public ResponseEntity<?> getAllCategories() {

        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);

    }

    /*
     * API - getAllQuestions
     *
     *    public API - No Authentication needed
     *
     *    questionService is called to get all categories available with details
     *
     *    HttPResponse is returned with questions
     *
     */
    @GetMapping("/api/questions/all")
    public ResponseEntity<?> getAllQuestions() {

        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);

    }
}
