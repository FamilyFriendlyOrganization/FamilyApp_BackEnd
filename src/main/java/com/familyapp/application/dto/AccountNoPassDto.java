package com.familyapp.application.dto;

import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class AccountNoPassDto {
    private UUID AccountId;
    private String Username;
    private String Email;
    private Byte AccountStatus;
    private String DisplayName;
}

