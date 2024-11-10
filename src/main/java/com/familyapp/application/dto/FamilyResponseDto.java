package com.familyapp.application.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FamilyResponseDto {
    private FamilyDto family;
    private UserDto user;
    private RoleDto role;
}
