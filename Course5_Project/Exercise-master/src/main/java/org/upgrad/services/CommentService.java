package org.upgrad.services;
import org.springframework.stereotype.Service;

@Service("CommentService")
public interface CommentService {

    void giveComment (String content , int userId, int answerId);

    int getUserByCommentId(int commentId);

    void editCommentByCommentId(int commentId ,String commentBody);

   // List<Comment> getAllCommentsByAnswer(int answerid);

    //void deleteComment(int id);

    //Answer editComment (int answerId);

}
