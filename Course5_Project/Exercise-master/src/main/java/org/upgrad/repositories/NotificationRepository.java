package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Notification;

import javax.transaction.Transactional;

@Repository
public interface NotificationRepository extends CrudRepository<Notification,Integer>{

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="insert into notification (user_id,message ,date,read) values (?1,?2,CURRENT_TIMESTAMP,FALSE )")
    void addNotificationForUser(int user, String notificationMessage);
}
