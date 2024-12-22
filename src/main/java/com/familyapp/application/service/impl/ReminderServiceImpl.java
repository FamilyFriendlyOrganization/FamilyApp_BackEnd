package com.familyapp.application.service.impl;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.ReminderDto;
import com.familyapp.application.entity.*;
import com.familyapp.application.exception.ResourceNotFoundException;
import com.familyapp.application.mapper.AccountMapper;
import com.familyapp.application.mapper.ReminderMapper;
import com.familyapp.application.repository.AccountRepository;
import com.familyapp.application.repository.FamilyRepository;
import com.familyapp.application.repository.ReminderRepository;
import com.familyapp.application.repository.UserRepository;
import com.familyapp.application.service.ReminderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    private AccountRepository accountRepository;
    private EmailServiceImpl emailService;
    @Override
    public ReminderDto createReminder(ReminderDto reminderDto) {
        Family family = familyRepository.findById(reminderDto.getFamilyId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account not found with given Id: " + reminderDto.getFamilyId()));
        User user = userRepository.findById(reminderDto.getUserId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account not found with given Id: " + reminderDto.getUserId()));
        Reminder reminder = ReminderMapper.toEntity(reminderDto,user,family);
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

    @Scheduled(cron = "0 0 12 * * ?") // Mỗi ngày vào lúc 12:00 AM
    public void sendReminderEmailsForToday() {
        List<Reminder> reminders = reminderRepository.findAll();  // Lấy tất cả các Reminder từ cơ sở dữ liệu

        // Lọc các reminder có ngày nhắc nhở là ngày hiện tại
        LocalDate today = LocalDate.now();
        List<Reminder> todayReminders = reminders.stream()
                .filter(reminder -> reminder.getRemindDate().toLocalDate().isEqual(today))
                .collect(Collectors.toList());

        // Gửi email cho mỗi reminder
        for (Reminder reminder : todayReminders) {
            // Gọi phương thức gửi email tới gia đình
            sendReminderEmailsToFamily(reminder);
        }
    }

    public void sendReminderEmailsToFamily(Reminder reminder) {
        // Lấy tất cả các thành viên thuộc gia đình
        UUID familyId = reminder.getFamily().getFamilyId();
        List<User> familyMembers = userRepository.findAllByFamily_FamilyId(familyId);

        // Lọc ra các thành viên có assignedAccountId
        List<String> emails = familyMembers.stream()
                .filter(user -> user.getAssignedAccountId() != null)
                .map(user -> accountRepository.findById(user.getAssignedAccountId().getAccountId())
                        .map(Account::getEmail)
                        .orElse(null))
                .filter(email -> email != null) // Loại bỏ null
                .collect(Collectors.toList());

        // Gửi email tới từng người
        for (String email : emails) {
            emailService.sendEmail(
                    email,
                    "Reminder Notification: " + reminder.getTitle(),
                    "Hi,\n\nThis is a friendly reminder for: " + reminder.getTitle() +
                            "\nDetails: " + reminder.getDescription() + "\n\nBest regards,\nFamilyApp Team"
            );
        }
    }


}
