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

    @Override
    public void deleteCommentByCommentId(int commentId) {
        commentRepository.deleteCommentById(commentId);

    }

    @Override
    public List<Comment> getAllCommentsByAnswerId(int answerId) {
        return commentRepository.getAllCommentsByAnswerId(answerId);
    }

}
