package org.upgrad.repositories;

import org.aspectj.weaver.ast.Not;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Notification;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
public interface NotificationRepository extends CrudRepository<Notification,Integer>{

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="insert into notification (user_id,message ,date,read) values (?1,?2,CURRENT_TIMESTAMP,FALSE )")
    void addNotificationForUser(int user, String notificationMessage);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="update notification set read='TRUE' where user_id=(?1) ")
    void setReadFlag(int userId);

    @Query(nativeQuery = true,value="Select * from NOTIFICATION where user_id=(?1) and read=(?2)")
    List<Map> getNewNotification(int id, Boolean bool);

    @Query(nativeQuery = true,value="Select * from NOTIFICATION where user_id=(?1) ")
    Iterable<Notification> getAllNotification(int id);
}
