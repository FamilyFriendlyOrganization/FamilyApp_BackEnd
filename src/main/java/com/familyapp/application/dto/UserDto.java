package com.familyapp.application.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private UUID UserId;
    private UUID AssignedAccountId;
    private UUID RoleId;
    private UUID FamilyId;
    private String Name;
    private byte Sex;
    private Date Birthday;
}
