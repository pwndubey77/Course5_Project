
package org.upgrad.services;

import org.springframework.stereotype.Service;
import java.util.*;
import org.upgrad.models.Question;
import org.upgrad.repositories.QuestionRepository;



@Service("QuestionService")
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {

        this.questionRepository =questionRepository;
    }



    @Override
    public void createQuestion(String content , Set<Integer> categories, int userId) {

        questionRepository.addQuestionValues(content,userId);
        int questionId = questionRepository.getLatestQuestionId ();
        for(int category : categories) {

           questionRepository.addCategory(questionId, category,Long.valueOf (questionId));
        }

    }

    @Override
    public List<Question> getAllQuestionsByUser(int userId) {
        return questionRepository.getAllQuestionsByUserId(userId);
    }

    @Override
    public int findUserByQuestionId(int questionId){
        return questionRepository.findUserByQuestionId(questionId);
    }

    @Override
    public void deleteQuestionById(int questionId) {

        questionRepository.deleteQuestionById(questionId);
    }

    @Override
    public Question getQuestionByQuestionId(int questionId) {
        return questionRepository.getQuestionsByQuestionId (questionId);
    }

    @Override
    public List<Question> getQuestionsByCategory(int categoryId) {

        Set<Integer> questionIds = questionRepository.getQuestionsByCategoryId (categoryId);

        return questionRepository.getQuestionsByQuestionId(questionIds);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.getAllQuestions();
    }


    @Override
    public int checkQuestionEntry(int questionid) {

        Long validQuestionId = questionRepository.checkQuestionEntry (questionid);

        if (validQuestionId == null) {
            return 0;
        } else
            return validQuestionId.intValue ();


    }

}







