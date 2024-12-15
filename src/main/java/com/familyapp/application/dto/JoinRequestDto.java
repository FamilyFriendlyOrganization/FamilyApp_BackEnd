package com.familyapp.application.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JoinRequestDto {
    private UUID joinRequestId;
    private UUID accountId;
    private String accountUsername;
    private UUID familyId;
    private String familyName;
    private String status; // Could be an enum or string representation of the status
    private LocalDateTime joinDate;
}
