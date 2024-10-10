package com.familyapp.application.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NotificationDto {
    private UUID NotificationId;
    private String Title;
    private String Message;
    private UUID FamilyId;
    private UUID UserId;
    private LocalDateTime NotificationDateTime;
    private byte Status;

}
