
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
    public void createQuestion(int questionId , String content , Set<Integer> categories, int userId) {

        questionRepository.addQuestionValues(questionId,content,userId);
        for(int category : categories) {
           Long idQuestionCategory = System.currentTimeMillis() % 1000;
           questionRepository.addCategory(idQuestionCategory.intValue (), questionId, category,Long.valueOf (questionId));
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

        Set<Integer> questions =  questionRepository.getQuestionsByCategoryId (categoryId);
        List<Question> allQuestions = new ArrayList<> ();

        for(int questionId : questions) {
            allQuestions.add (questionRepository.getQuestionsByQuestionId (questionId));
        }

        return allQuestions;
    }


    /*
    @Override
    public Question findQuestionById(int questionid) {
        return questionRepository.readQuestion (questionid);
    }




    @Override
    public void addQuestionValues(int id,String body, int userId){ questionRepository.addQuestionValues(id,body,userId);}

    @Override
    public Iterable<Question> getAll() {
        return questionRepository.findAll();
    }



    */
}







