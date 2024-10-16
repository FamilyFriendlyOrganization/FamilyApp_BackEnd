package com.familyapp.application.dto;

import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class AccountDto {
    private UUID AccountId;
    private String Username;
    private String Password;
    private String Email;
    private Byte AccountStatus;
    private String DisplayName;
}





