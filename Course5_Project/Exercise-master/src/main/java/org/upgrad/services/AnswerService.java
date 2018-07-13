package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Answer;

import java.util.List;


public interface AnswerService {


    public void addAnswer(String answerBody, int userId, int questionId);


    int findUserByAnswerId(int answerId);

    void editAnswerByAnswerId(int answerId ,String answerBody);

    List<Answer> getAllAnswersByQuestionId(int questionId);

    List<Answer> getAllAnswersByUser(int userId);
}

