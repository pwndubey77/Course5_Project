package org.upgrad.services;
import org.springframework.stereotype.Service;
import java.util.*;
import org.upgrad.models.Comment;
import org.upgrad.repositories.CommentRepository;

public class CommentServiceImpl {

    @Service("CommentService")
    public class CommentServiceImpl implements CommentService {

        private final CommentRepository commentRepository;

        public CommentServiceImpl(CommentRepository commentRepository) {

            this.commentRepository =commentRepository;
        }

        @Override
        public void giveQuestion(int commentId , String content ,int userId) {
            commentRepository.addcommentValues(commentId,content,userId);
        }

        @Override
        public List<Comment> getAllCommentsByAnswer (int answerid) {
            return CommentRepository.getAllQuestionsByUserId(answerid);
        }

        @Override
        public int editComment (int commentId){
            return commentRepository.editComment(CommentId);
        }

        @Override
        public void deletecomment(int commentId) {

            commentRepository.deletecomment(commentId);
        }

        }
