package org.upgrad.services;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.upgrad.models.Comment;
import javafx.geometry.Pos;
import org.upgrad.models.answer;
import org.upgrad.models.question;
import org.upgrad.models.User;

@Service("CommentService")
public interface CommentService {

    void giveComment (int id , String content , int userId);

    List<Comment> getAllCommentsByAnswer(int answerid);

    void deleteComment(int id);

    Answer editComment (int answerId);

}
