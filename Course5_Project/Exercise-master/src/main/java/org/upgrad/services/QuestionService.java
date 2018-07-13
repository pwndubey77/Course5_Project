
package org.upgrad.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.upgrad.models.Question;
import javafx.geometry.Pos;
import org.upgrad.models.User;

@Service("QuestionService")
public interface QuestionService {

    void createQuestion(int id , String content , int userId);

    List<Question> getAllQuestionsByUser(int userId);

    int findUserByQuestionId(int questionId);

    void deleteQuestionById(int questionId);

    /*
    Iterable<Question> getAll();
    Question findQuestionById(int questionId);



    List<Question> findAll();
    //List<Question> firstThreeQuestions();
    */

}



