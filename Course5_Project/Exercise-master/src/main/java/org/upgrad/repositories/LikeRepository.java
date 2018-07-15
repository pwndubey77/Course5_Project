package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Like;

import javax.transaction.Transactional;

@Repository
public interface LikeRepository extends CrudRepository<Like,Integer>{

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="insert into likes (user_id,answer_id) values (?1,?2)")
    void addLikesByUserForAnswerId(int userId,int answerId );


    @Query(nativeQuery = true,value="SELECT id from likes where user_id = ?1 AND answer_id = ?2")
    Long checkForUserInLikedByEntries(int userId,int answer_id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="delete from likes where user_id = ?1 AND answer_id = ?2")
    void unlikeAnswer(int userId,int answerId);
}
