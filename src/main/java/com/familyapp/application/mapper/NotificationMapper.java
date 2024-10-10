package com.familyapp.application.mapper;

import com.familyapp.application.dto.NotificationDto;
import com.familyapp.application.entity.Family;
import com.familyapp.application.entity.Notification;
import com.familyapp.application.entity.User;

public class NotificationMapper {
    public static NotificationDto toDto(Notification notification) {
        if (notification == null) {
            return null;
        }
        return new NotificationDto(
                notification.getNotificationId(),
                notification.getTitle(),
                notification.getMessage(),
                notification.getFamily().getFamilyId(),
                notification.getUser().getUserId(),
                notification.getNotificationDateTime(),
                notification.getStatus()
        );
    }
    public static Notification toEntity(NotificationDto notificationDto, User user,Family family) {
        if (notificationDto == null) {
            return null;
        }
        Notification notification = new Notification();
        notification.setNotificationId(notificationDto.getNotificationId());
        notification.setTitle(notificationDto.getTitle());
        notification.setMessage(notificationDto.getMessage());
        notification.setFamily(family);
        notification.setUser(user);
        notification.setNotificationDateTime(notificationDto.getNotificationDateTime());
        notification.setStatus(notificationDto.getStatus());
        return notification;
    }
}
