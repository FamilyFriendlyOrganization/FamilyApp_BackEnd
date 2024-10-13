package com.familyapp.application.controller;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.ReminderDto;
import com.familyapp.application.entity.Family;
import com.familyapp.application.entity.User;
import com.familyapp.application.service.ReminderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/reminder")

public class ReminderController {
    @Autowired
    private ReminderService ReminderService;
    @PostMapping
    public ResponseEntity<ReminderDto> createReminder(@RequestBody ReminderDto reminderDto, User user, Family family) {
        return new ResponseEntity<>(ReminderService.createReminder(reminderDto, user,family), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReminderDto> getReminderById(@PathVariable("id") UUID reminderId){
        ReminderDto reminderDto = ReminderService.getReminderbyId(reminderId);
        return ResponseEntity.ok(reminderDto);
    }

    @GetMapping
    public ResponseEntity<List<ReminderDto>> getAllReminder(){
        List<ReminderDto> reminderDtos = ReminderService.getAllReminder();
        return ResponseEntity.ok(reminderDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<ReminderDto> updateReminder( @RequestBody ReminderDto reminderDto,
                                                       @PathVariable("id") UUID reminderId){
        return ResponseEntity.ok(ReminderService.updateReminder(reminderId, reminderDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteReminder(@PathVariable("id") UUID reminderId){
        ReminderService.deleteReminder(reminderId);
        return ResponseEntity.ok("Reminder deleted successfully");
    }
}
