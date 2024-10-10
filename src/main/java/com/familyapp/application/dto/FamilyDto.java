package com.familyapp.application.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FamilyDto {
    private UUID FamilyId;
    private UUID CreatedAccountId;
    private int Budget;
    private String FamilyName;
}
