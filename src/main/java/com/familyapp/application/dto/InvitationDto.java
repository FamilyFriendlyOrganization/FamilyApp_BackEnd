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
public class InvitationDto {
    private UUID inviteId; // Unique identifier for the invitation
    private String inviteCode; // Unique invitation code
    private UUID familyId; // ID of the family associated with the invitation
    private LocalDateTime expirationTime; // Expiration time for the invitation
    private boolean isUsed; // Indicates if the invitation has been used

}
