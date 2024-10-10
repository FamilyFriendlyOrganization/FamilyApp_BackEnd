package com.familyapp.application.mapper;

import com.familyapp.application.dto.ReminderDto;
import com.familyapp.application.entity.Family;
import com.familyapp.application.entity.Reminder;
import com.familyapp.application.entity.User;

public class ReminderMapper {
    public static ReminderDto toDto(Reminder reminder) {
        if (reminder == null) {
            return null;
        }
        return new ReminderDto(
                reminder.getReminderId(),
                reminder.getTitle(),
                reminder.getDescription(),
                reminder.getRemindDate(),
                reminder.getStatus(),
                reminder.getFamily().getFamilyId(),
                reminder.getUser().getUserId()
        );
    }
    public static Reminder toEntity(ReminderDto dto, User user, Family family) {
        if (dto == null) {
            return null;
        }
        Reminder reminder = new Reminder();
        reminder.setReminderId(dto.getReminderId());
        reminder.setTitle(dto.getTitle());
        reminder.setDescription(dto.getDescription());
        reminder.setRemindDate(dto.getRemindDate());
        reminder.setStatus(dto.getStatus());
        reminder.setFamily(family);
        reminder.setUser(user);
        return reminder;
    }
}
