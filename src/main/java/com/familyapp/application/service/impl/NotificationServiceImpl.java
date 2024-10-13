package com.familyapp.application.service.impl;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.NotificationDto;
import com.familyapp.application.entity.Account;
import com.familyapp.application.entity.Family;
import com.familyapp.application.entity.Notification;
import com.familyapp.application.entity.User;
import com.familyapp.application.exception.ResourceNotFoundException;
import com.familyapp.application.mapper.AccountMapper;
import com.familyapp.application.mapper.NotificationMapper;
import com.familyapp.application.repository.FamilyRepository;
import com.familyapp.application.repository.NotificationRepository;
import com.familyapp.application.repository.UserRepository;
import com.familyapp.application.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    private FamilyRepository familyRepository;
    private UserRepository userRepository;
    @Override
    public NotificationDto createNotification(NotificationDto notificationDto, User user, Family family) {
        Notification notification = NotificationMapper.toEntity(notificationDto, user, family);
        Notification savedNotification = notificationRepository.save(notification);
        return NotificationMapper.toDto(savedNotification);
    }


    public NotificationDto getNotificationbyId(UUID notificationId) {
        Notification Notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found " + notificationId));
        return NotificationMapper.toDto(Notification);
    }

    @Override
    public NotificationDto updateNotification(UUID notificationId,NotificationDto updatedNotificationDto) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Notification is not exist with given Id: " + notificationId));
        notification.setTitle(updatedNotificationDto.getTitle());
        notification.setMessage(updatedNotificationDto.getMessage());
        Family family = familyRepository.findById(updatedNotificationDto.getFamilyId())
                .orElseThrow(() -> new ResourceNotFoundException("Family does not exist with given Id: " + updatedNotificationDto.getFamilyId()));
        notification.setFamily(family);
        User user = userRepository.findById(updatedNotificationDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with given Id: " + updatedNotificationDto.getUserId()));
        notification.setUser(user);
        notification.setNotificationDateTime(updatedNotificationDto.getNotificationDateTime());
        notification.setStatus(updatedNotificationDto.getStatus());
        Notification updatedNotification = notificationRepository.save(notification);

        return NotificationMapper.toDto(updatedNotification);
    }

    @Override
    public List<NotificationDto> getAllNotification() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream().map((notification -> NotificationMapper.toDto(notification)))
                .collect(Collectors.toList());
    }


    @Override
    public void deleteNotification(UUID NotificationId) {
        Notification Notification = notificationRepository.findById(NotificationId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Notification is not exist with given Id: " + NotificationId));
        notificationRepository.deleteById(NotificationId);
    }
}
