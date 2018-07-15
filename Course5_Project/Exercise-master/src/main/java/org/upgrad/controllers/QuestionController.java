
package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.Question;
import org.upgrad.models.User;
import org.upgrad.services.CategoryService;
import org.upgrad.services.QuestionService;
import org.upgrad.services.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;

import org.upgrad.repositories.QuestionRepository;
import org.upgrad.repositories.UserRepository;

import java.util.Set;


@RestController
public class QuestionController {

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @Autowired
    CategoryService categoryService;

    /*
     * API - createQuestion
     *
     * @parameter "content" and "categorId"is provided by user and,session (HttpSession ) gives details of session
     *
     * -- Session details are checked for authentication - if fails -> Unauthorized - goes to return httpStatus,
     *                                                       - if pass -> call respective services ,
     *
     * -- questionService called to add entry with questionBody,categories and user to add entries in database
     *
     * -- return response body + HttpStatus
     *
     */
    @PostMapping("api/question")
    public ResponseEntity<?> createQuestion(@RequestParam("content") String body,@RequestParam("categoryId") Set<Integer> categories, HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {

            Long checkCategory = 0L;

            for(int category : categories) {

                checkCategory =  categoryService.checkCategory (category);
                if(checkCategory == null){
                    return new ResponseEntity<>("There is no category with categoryId " + category+" Please enter correct categories", HttpStatus.NOT_FOUND);
                }
            }


            questionService.createQuestion (body,categories,userService.getUserID ((String) session.getAttribute("currUser")));

            return new ResponseEntity<>("Question added successfully", HttpStatus.OK);

        }
    }

    /*
     * API - getAllQuestionsByUser
     *
     *    session (HttpSession ) gives details of session
     *
     * -- Session details are checked for authentication - if fails -> Unauthorized - goes to return httpStatus,
     *                                                   - if pass -> questionService
     *
     * -- questionService checks entries of questions from database with respect to the user
     *
     * -- return response body + HttpStatus
     *
     */

    @GetMapping("/api/question/all")
    public ResponseEntity<?> getAllQuestionsByUser(HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {
            return new ResponseEntity<>(questionService.getAllQuestionsByUser(userService.getUserID ((String) session.getAttribute("currUser"))), HttpStatus.OK);
        }
    }

    /*
     * API - deleteQuestionByQuestionId
     *
     *    @prameter "questionId" is provided by user and session (HttpSession ) gives details of session
     *
     * -- Session details are checked for authentication - if fails -> Unauthorized - goes to return httpStatus,
     *                                                   - if pass -> call user service to authenticate user is admin/user (who added the question)
     *
     * -- questionService deletes entries for question from database with respect to the user
     *
     * -- return response body + HttpStatus
     *
     */
    @DeleteMapping("/api/question/{questionId}")
    public ResponseEntity<?> deleteQuestionByQuestionId(@RequestParam("questionId") int questionId,HttpSession session) {


        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {

            if(questionService.checkQuestionEntry (questionId) > 0){
                String userRole = userService.getCurrentUserRole((String) session.getAttribute("currUser"));

                int userId = questionService.findUserByQuestionId (questionId);
                if(userId == (userService.getUserID ((String) session.getAttribute("currUser"))) || userRole.equalsIgnoreCase ("admin")){
                    questionService.deleteQuestionById (questionId);
                    return new ResponseEntity<>("Question with questionId " + questionId + " deleted successfully", HttpStatus.OK);
                }

                else{
                    return new ResponseEntity<>("You do not have rights to delete this question!", HttpStatus.FORBIDDEN);
                }
            }
            else{
                return new ResponseEntity<>("There is no Question with questionId " + questionId, HttpStatus.NOT_FOUND);
            }
        }

    }

    /*
     * API - deleteQuestionByQuestionId
     *
     *    @parameter "categoryId" is provided by user and session (HttpSession ) gives details of session
     *
     * -- Session details are checked for authentication - if fails -> Unauthorized - goes to return httpStatus,
     *                                                   - if pass -> questionservice
     *
     * -- questionService checks entries of questions from database with respect to the category
     *
     * -- return response body + HttpStatus
     *
     */
    @GetMapping("/api/question/all/{categoryId}")
    public ResponseEntity<?> getAllQuestionsByCategory(@RequestParam("categoryId") int categoryId, HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {

            return new ResponseEntity<>(questionService.getQuestionsByCategory(categoryId), HttpStatus.OK);
        }
    }





}
