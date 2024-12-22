package com.familyapp.application.service;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.ReminderDto;
import com.familyapp.application.entity.Family;
import com.familyapp.application.entity.Reminder;
import com.familyapp.application.entity.User;

import java.util.List;
import java.util.UUID;

public interface ReminderService {
    ReminderDto createReminder(ReminderDto reminderDto);
    ReminderDto getReminderbyId(UUID reminderId);
    ReminderDto updateReminder(UUID reminderId, ReminderDto reminderDto);
    List<ReminderDto> getAllReminder();
    void deleteReminder(UUID reminderId);

    void sendReminderEmailsForToday();
}
