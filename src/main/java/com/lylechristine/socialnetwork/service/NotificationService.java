package com.lylechristine.socialnetwork.service;

import com.lylechristine.socialnetwork.model.Notification;
import com.lylechristine.socialnetwork.repository.NotificationRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationRepository notifications;
    private final UserService users;
    private final SimpMessagingTemplate messaging;

    public NotificationService(NotificationRepository notifications, UserService users, SimpMessagingTemplate messaging) {
        this.notifications = notifications;
        this.users = users;
        this.messaging = messaging;
    }

    public Notification notifyUser(String username, String message, String link) {
        Notification notification = new Notification();
        notification.setRecipient(users.require(username));
        notification.setMessage(message);
        notification.setLink(link);
        Notification saved = notifications.save(notification);
        messaging.convertAndSendToUser(username, "/queue/notifications", saved);
        return saved;
    }
}
