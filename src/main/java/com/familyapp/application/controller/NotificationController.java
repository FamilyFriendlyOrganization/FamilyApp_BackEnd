package com.familyapp.application.controller;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.NotificationDto;
import com.familyapp.application.entity.Family;
import com.familyapp.application.entity.User;
import com.familyapp.application.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/notification")

public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @PostMapping
    public ResponseEntity<NotificationDto> createNotification(@RequestBody NotificationDto notificationDto, User user, Family family) {
        return new ResponseEntity<>(notificationService.createNotification(notificationDto, user, family), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<NotificationDto> getNotificationById(@PathVariable("id") UUID NotificationId){
        NotificationDto notificationDto = notificationService.getNotificationbyId(NotificationId);
        return ResponseEntity.ok(notificationDto);
    }

    @GetMapping
    public ResponseEntity<List<NotificationDto>> getAllNotification(){
        List<NotificationDto> notificationDtos = notificationService.getAllNotification();
        return ResponseEntity.ok(notificationDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<NotificationDto> updateNotification( @RequestBody NotificationDto notificationDto,
                                                       @PathVariable("id") UUID NotificationId){
        return ResponseEntity.ok(notificationService.updateNotification(NotificationId, notificationDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable("id") UUID notificationId){
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.ok("Notification deleted successfully");
    }
}
