package com.familyapp.application.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReminderDto {
    private UUID ReminderId;
    private String Title;
    private String Description;
    private LocalDateTime RemindDate;
    private byte Status;

}
