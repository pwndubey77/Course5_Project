package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Comment;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment,String>{

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="insert into comment (content,date ,user_id,answer_id) values (?1,CURRENT_TIMESTAMP ,?2,?3)")
    void addCommentValues(String content, int userId,int answerId );

    @Query(nativeQuery = true,value="select user_id from comment where id = ?1")
    int getUserByCommentId(int commentId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="update comment set content=?2,modifiedon=now() where id=?1")
    void editCommentById(int commentId , String content);

    @Query(nativeQuery = true,value ="select * from comment where answer_id = ?1")
    List<Comment> getAllCommentsByAnswerId(int answerId);


    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="delete from comment where id=?1")
    void deleteCommentById(int commentId);
}
