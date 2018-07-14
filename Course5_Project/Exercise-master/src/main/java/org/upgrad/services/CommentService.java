package org.upgrad.services;

import org.upgrad.models.Comment;

import java.util.List;

public interface CommentService {

    void giveComment (String content , int userId, int answerId);

    int getUserByCommentId(int commentId);

    void editCommentByCommentId(int commentId ,String commentBody);

   // List<Comment> getAllCommentsByAnswer(int answerid);

    void deleteCommentByCommentId(int commentId);

    List<Comment> getAllCommentsByAnswerId(int answerId);
}
