package com.familyapp.application.service;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.NotificationDto;
import com.familyapp.application.entity.Family;
import com.familyapp.application.entity.User;

import java.util.List;
import java.util.UUID;

public interface NotificationService {
    NotificationDto createNotification(NotificationDto notificationDto);
    NotificationDto getNotificationbyId(UUID notificationId);
    NotificationDto updateNotification(UUID notificationId, NotificationDto notificationDto);
    List<NotificationDto> getAllNotification();
    void deleteNotification(UUID notificationId);
}
