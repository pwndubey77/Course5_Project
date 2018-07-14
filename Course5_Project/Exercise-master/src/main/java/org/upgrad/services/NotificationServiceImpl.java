package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.repositories.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService{

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void sendNotificationToUser(int user, String NotificationMessage) {

        notificationRepository.addNotifcationForUser(user,NotificationMessage);
    }
}
