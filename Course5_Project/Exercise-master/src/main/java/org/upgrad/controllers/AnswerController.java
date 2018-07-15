package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.User;
import org.upgrad.services.AnswerService;
import org.upgrad.services.NotificationService;
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

    @Autowired
    NotificationService notificationService;

    /*
     * API - createAnswer
     *
     * @parameter "questionId" and "answerBody"is provided by user and,session (HttpSession ) gives details of session
     *
     * -- Session details are checked for authentication - if fails -> Unauthorized - goes to return httpStatus,
     *                                                       - if pass -> call respective services ,
     *
     * -- answerService called to add entry with user,answerBody and questionId to add entries in database
     *
     * -- notification Services called update notifcation for user who asked question
     *
     * -- return response body + HttpStatus
     *
     */
    @PostMapping("api/answer")
    public ResponseEntity<?> createAnswer(@RequestParam("answer") String answerBody,@RequestParam("questionId") int questionId,HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {

            if(questionService.checkQuestionEntry (questionId) > 0){
                int userId = userService.getUserID((String) session.getAttribute ("currUser"));

                String notificationMessage = ("User with userId " + userId + " has answered your question with questionId " + questionId);

                answerService.addAnswer (answerBody,questionService.findUserByQuestionId (questionId),questionId);

                notificationService.sendNotificationToUser (userId,notificationMessage);

                return new ResponseEntity<>("Answer to questionId "+questionId + " added successfully", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("There is no Question with questionId " + questionId, HttpStatus.NOT_FOUND);
            }
        }
    }

    /*
     * API - editAnswer
     *
     * @parameter "answerId" and "answerBody"is provided by user and,session (HttpSession ) gives details of session
     *
     * -- Session details are checked for authentication - if fails -> Unauthorized - goes to return httpStatus,
     *                                                   - if pass -> call user service to authenticate user is admin/user (who added the answer) ,
     *
     * -- answerService called to modify the  entry with user,answerBody and questionId to add entries in database
     *
     * -- return response body + HttpStatus
     *
     */
    @PutMapping("/api/answer/{answerId}")
    public ResponseEntity<?> editAnswer(@RequestParam("answer") String answerBody,@RequestParam("answerId") int answerId,HttpSession session) {




        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {

            if(answerService.checkAnswerEntry (answerId) > 0){
                String userRole = userService.getCurrentUserRole((String) session.getAttribute("currUser"));
                int userId = answerService.findUserByAnswerId (answerId);

                if(userId == (userService.getUserID ((String) session.getAttribute("currUser"))) || userRole.equalsIgnoreCase ("admin")){

                    answerService.editAnswerByAnswerId (answerId,answerBody);
                    return new ResponseEntity<>("Answer with answerId "+answerId + " edited successfully", HttpStatus.OK);
                }

                else{
                    return new ResponseEntity<>("You do not have rights to edit this answer!", HttpStatus.UNAUTHORIZED);
                }
            }
            else{
                    return new ResponseEntity<>("There is no Answer with answerId " + answerId, HttpStatus.NOT_FOUND);
                }

        }

}


    /*
     * API - getAllAnswersToQuestion
     *
     * @parameter "questionId" is provided by user and,session (HttpSession ) gives details of session
     *
     * -- Session details are checked for authentication - if fails -> Unauthorized - goes to return httpStatus,
     *                                                   - if pass -> call answer service
     *
     * -- answerService reads entries from database with respect to questionId
     *
     * -- return response body + HttpStatus
     *
     */

    @GetMapping("/api/answer/all/{questionId}")
    public ResponseEntity<?> getAllAnswersToQuestion(@RequestParam("questionId") int questionId,HttpSession session) {


        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {
            if(questionService.checkQuestionEntry (questionId) > 0){
                return new ResponseEntity<>(answerService.getAllAnswersByQuestionId(questionId), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("There is no Question with questionId " + questionId, HttpStatus.NOT_FOUND);
            }
        }
    }

    /*
     * API - getAllAnswersByUser
     *
     *    session (HttpSession ) gives details of session
     *
     * -- Session details are checked for authentication - if fails -> Unauthorized - goes to return httpStatus,
     *                                                   - if pass -> call user service for user details
     *
     * -- answerService reads entries for answer from database with respect to the user
     *
     * -- return response body + HttpStatus
     *
     */
    @GetMapping("/api/answer/all")
    public ResponseEntity<?> getAllAnswersByUser(HttpSession session) {



        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {
            int currentUser = userService.getUserID ((String) session.getAttribute ("currUser"));
            return new ResponseEntity<>(answerService.getAllAnswersByUser(currentUser), HttpStatus.OK);
        }
    }

    /*
     * API - deleteAnswer
     *
     *    @prameter "answerId" is provided by user and session (HttpSession ) gives details of session
     *
     * -- Session details are checked for authentication - if fails -> Unauthorized - goes to return httpStatus,
     *                                                   - if pass -> call user service to authenticate user is admin/user (who added the answer
     *
     * -- answerService deletes entries for answer from database with respect to the user
     *
     * -- return response body + HttpStatus
     *
     */
    @DeleteMapping("/api/answer/{answerId}")
    public ResponseEntity<?> deleteAnswer(@RequestParam("answerId") int answerId,HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {

            if(answerService.checkAnswerEntry (answerId) > 0){
                String userRole = userService.getCurrentUserRole((String) session.getAttribute("currUser"));
                int userId = answerService.findUserByAnswerId (answerId);

                if(userId == (userService.getUserID ((String) session.getAttribute("currUser"))) || userRole.equalsIgnoreCase ("admin")){
                    answerService.deleteAnswerById (answerId);
                    return new ResponseEntity<>("Answer with answerId " + answerId + " deleted successfully", HttpStatus.OK);
                }

                else{
                    return new ResponseEntity<>("You do not have rights to delete this answer!", HttpStatus.UNAUTHORIZED);
                }
            }

            else{
                return new ResponseEntity<>("There is no Answer with answerId " + answerId, HttpStatus.NOT_FOUND);
            }



        }

    }


    /*
     * API - getAllAnswersByLikes
     *
     * @parameter "questionId" is provided by user and,session (HttpSession ) gives details of session
     *
     * -- Session details are checked for authentication - if fails -> Unauthorized - goes to return httpStatus,
     *                                                   - if pass -> call answer service
     *
     * -- answerService reads entries from database with respect to questionId and sort answers based on no. of likes
     *
     * -- return response body + HttpStatus
     *
     */

    @GetMapping("/api/answer/likes/{questionId}")
    public ResponseEntity<?> getAllAnswersByLikes(@RequestParam("questionId") int questionId,HttpSession session) {

        if (session.getAttribute("currUser")==null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {

            if(questionService.checkQuestionEntry (questionId) > 0){
                String user=session.getAttribute("currUser").toString();
                int userId=userService.getUserID(user);
                return new ResponseEntity<>(answerService.getAllAnswersByLikes(questionId,userId), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("There is no Question with questionId " + questionId, HttpStatus.NOT_FOUND);
            }

        }
    }

}