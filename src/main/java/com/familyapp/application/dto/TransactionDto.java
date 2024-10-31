package com.familyapp.application.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionDto {
    private UUID TransactionId;
    private String TransactionName;
    private String Description;
    private Byte TransactionType;
    private Integer Amount;
    private UUID CategoryId;
    private Integer Status;
    private UUID FamilyId;
    private UUID UserId;
}
