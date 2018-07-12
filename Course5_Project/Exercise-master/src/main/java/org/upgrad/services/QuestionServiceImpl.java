package org.upgrad.services;

import org.springframework.stereotype.Service;

import java.util.List;
import org.upgrad.models.Question;
import javafx.geometry.Pos;
import org.springframework.stereotype.Service;
import org.upgrad.models.User;
import org.upgrad.repositories.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

@Service("QuestionService")
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {

        this.questionRepository =questionRepository;
    }



    @Override
    public void createQuestion(int id , String content , int userId) {

        questionRepository.addQuestionValues(id,content,userId);
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






