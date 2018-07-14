package org.upgrad.services;

import org.upgrad.models.Notification;

import java.util.List;
import java.util.Map;

public interface NotificationService {

    public void sendNotificationToUser(int user, String NotificationMessage);
    public void setFlag(int userId);
    List<Map> getAllUnreadNotificationByUser(int id, Boolean bool);
    Iterable<Notification> getAllNotification(int userId);
    public void setReadFlag(int id);
}
