package org.upgrad.services;
import org.springframework.stereotype.Service;

@Service("CommentService")
public interface CommentService {

    void giveComment (String content , int userId, int answerId);

   // List<Comment> getAllCommentsByAnswer(int answerid);

    //void deleteComment(int id);

    //Answer editComment (int answerId);

}
