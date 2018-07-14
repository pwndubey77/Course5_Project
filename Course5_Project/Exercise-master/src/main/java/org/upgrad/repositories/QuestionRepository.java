
package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Question;
import org.upgrad.models.User;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="insert into question (content,date ,user_id) values (?1,CURRENT_TIMESTAMP ,?2)")
    void addQuestionValues(String content, int user_id);

    @Query(nativeQuery = true,value="select * from question where user_id=?1")
    List<Question> getAllQuestionsByUserId(int userId);

    @Query(nativeQuery = true,value="select user_id from question where id=?1")
    int findUserByQuestionId(int questionId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="delete from question where id=?1 ")
    void deleteQuestionById(int questionid);

    @Query(nativeQuery = true,value="select * from question where id=?1")
    Question getQuestionsByQuestionId(int questionId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="insert into question_category (question_id,category_id,questions_id) values (?1,?2,?3)")
    void addCategory(int question_id, int category_id,long questions_id);


    @Query(nativeQuery = true, value="select question_id from question_category where category_id=?1")
    Set<Integer> getQuestionsByCategoryId(int categoryId);

    @Query(nativeQuery = true, value="select max(id)  from question")
    int getLatestQuestionId();

    @Query(nativeQuery = true,value="select * from question ")
    List<Question> getAllQuestions();

    @Query(nativeQuery = true,value="select * from question where id IN (?1)")
    List<Question> getQuestionsByQuestionId(Set<Integer> id);

    /*


    @Query(nativeQuery = true,value="select id from question where id=?1")
    Question findQuestionById(int id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="insert into likes (id,post_id,user_user_name) values (?1,?2,?3)")
    void addLikes(Long likesId,Long postId,String uname);

    @Query(nativeQuery = true,value="select * from likes where post_id=?1")
    Iterable<String> getLikesbyPost(int id);

    @Query(nativeQuery = true, value="select id from likes where post_id = ?1 AND user_user_name=?2")
    Long checkLikes(Long postId,String uname);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="insert into comments (id,post_id,user_user_name,comment) values (?1,?2,?3,?4)")
    void addComment(Long commentsId,int postid,String uname,String comment);

    @Query(nativeQuery = true,value="select * from comments where post_id=?1")
    Iterable<String> getCommentbyPost(int id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="delete from comments where post_id=?1 ")
    void deleteCommentsById(Long id);


    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="delete from likes where post_id=?1 ")
    void deleteLikesById(Long id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="delete from post where id=?1 ")
    void deletePostById(Long id);
    */

}