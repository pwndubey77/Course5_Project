package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Answer;
import org.upgrad.repositories.AnswerRepository;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService{

    private final AnswerRepository answerRepository;

    AnswerServiceImpl(AnswerRepository answerRepository ){

        this.answerRepository = answerRepository;
    }

    @Override
    public void addAnswer(String answerBody, int userId, int questionId) {

        answerRepository.addAnswerToQuestionById (answerBody,userId,questionId);
    }

    @Override
    public int findUserByAnswerId(int answerId) {
        return answerRepository.getUserByAnswerId(answerId);
    }

    @Override
    public void editAnswerByAnswerId(int answerId , String answerBody ) {
        answerRepository.editAnswerById(answerId,answerBody);
    }

    @Override
    public List<Answer> getAllAnswersByQuestionId(int questionId) {
        return answerRepository.getAllAnswersByQuestionId(questionId);
    }

    @Override
    public List<Answer> getAllAnswersByUser(int userId) {
        return answerRepository.getAllAnswersByUserId(userId);
    }

    @Override
    public void deleteAnswerById(int answerId) {
        answerRepository.deleteAnswerByAnswerId(answerId);
    }
}
