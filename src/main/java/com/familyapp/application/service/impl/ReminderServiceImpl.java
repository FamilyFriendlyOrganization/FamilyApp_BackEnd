package com.familyapp.application.service.impl;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.ReminderDto;
import com.familyapp.application.entity.*;
import com.familyapp.application.exception.ResourceNotFoundException;
import com.familyapp.application.mapper.AccountMapper;
import com.familyapp.application.mapper.ReminderMapper;
import com.familyapp.application.repository.FamilyRepository;
import com.familyapp.application.repository.ReminderRepository;
import com.familyapp.application.repository.UserRepository;
import com.familyapp.application.service.ReminderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ReminderServiceImpl implements ReminderService {
    @Autowired
    private ReminderRepository reminderRepository;
    private UserRepository userRepository;
    private FamilyRepository familyRepository;
    @Override
    public ReminderDto createReminder(ReminderDto reminderDto, User user, Family family) {
        Reminder reminder = ReminderMapper.toEntity(reminderDto, user, family);
        Reminder savedReminder = reminderRepository.save(reminder);
        return ReminderMapper.toDto(savedReminder);
    }

    public ReminderDto getReminderbyId(UUID reminderId) {
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found " + reminderId));
        return ReminderMapper.toDto(reminder);
    }

    @Override
    public ReminderDto updateReminder(UUID reminderId,ReminderDto updatedReminderDto) {
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Reminder is not exist with given Id: " + reminderId));

        reminder.setTitle(updatedReminderDto.getTitle());
        reminder.setDescription(updatedReminderDto.getDescription());
        reminder.setRemindDate(updatedReminderDto.getRemindDate());
        reminder.setStatus(updatedReminderDto.getStatus());
        Family family = familyRepository.findById(updatedReminderDto.getFamilyId())
                .orElseThrow(() -> new ResourceNotFoundException("Family does not exist with the given Id: " + updatedReminderDto.getFamilyId()));
        reminder.setFamily(family);
        User user = userRepository.findById(updatedReminderDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with the given Id: " + updatedReminderDto.getUserId()));
        reminder.setUser(user);
        Reminder updatedReminder = reminderRepository.save(reminder);
        return ReminderMapper.toDto(updatedReminder);
    }

    @Override
    public List<ReminderDto> getAllReminder() {
        List<Reminder> reminders = reminderRepository.findAll();
        return reminders.stream().map((reminder -> ReminderMapper.toDto(reminder)))
                .collect(Collectors.toList());
    }


    @Override
    public void deleteReminder(UUID ReminderId) {
        Reminder Reminder = reminderRepository.findById(ReminderId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Reminder is not exist with given Id: " + ReminderId));
        reminderRepository.deleteById(ReminderId);
    }
}
