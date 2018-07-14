package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Notification;
import org.upgrad.repositories.NotificationRepository;

import java.util.List;
import java.util.Map;

@Service
public class NotificationServiceImpl implements NotificationService{

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void sendNotificationToUser(int user, String NotificationMessage) {

        notificationRepository.addNotificationForUser(user,NotificationMessage);
    }

    @Override
    public void setFlag(int userId) {
        notificationRepository.setReadFlag(userId);
    }
    @Override
    public List<Map> getAllUnreadNotificationByUser(int id, Boolean bool) {
        return notificationRepository.getNewNotification(id, bool);
    }

    @Override
    public Iterable<Notification> getAllNotification(int userId) {
        return notificationRepository.getAllNotification(userId);
    }

    @Override
    public void setReadFlag(int id) {
        notificationRepository.setReadFlag(id);
    }
}