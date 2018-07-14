package org.upgrad.services;
import org.springframework.stereotype.Service;
import java.util.*;
import org.upgrad.models.Comment;
import org.upgrad.repositories.CommentRepository;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {

        private final CommentRepository commentRepository;

        public CommentServiceImpl(CommentRepository commentRepository) {

            this.commentRepository =commentRepository;
        }

        @Override
        public void giveComment(String content ,int userId,int answerId) {
            commentRepository.addCommentValues(content,userId,answerId);
        }

        @Override
        public int getUserByCommentId(int commentId) {
            return commentRepository.getUserByCommentId(commentId);
        }

        @Override
        public void editCommentByCommentId(int commentId, String commentBody) {
            commentRepository.editCommentById (commentId,commentBody);
        }
/*
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
*/


}
