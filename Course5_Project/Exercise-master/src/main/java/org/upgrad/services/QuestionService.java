
package org.upgrad.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.upgrad.models.Question;
import javafx.geometry.Pos;
import org.upgrad.models.User;

@Service("QuestionService")
public interface QuestionService {

    void createQuestion(int id , String content , Set<Integer> categories, int userId);

    List<Question> getAllQuestionsByUser(int userId);

    int findUserByQuestionId(int questionId);

    void deleteQuestionById(int questionId);

    Question getQuestionByQuestionId(int questionId);

    List<Question> getQuestionsByCategory(int categoryId);


    /*
    Iterable<Question> getAll();
    Question findQuestionById(int questionId);



    List<Question> findAll();
    //List<Question> firstThreeQuestions();
    */

}



