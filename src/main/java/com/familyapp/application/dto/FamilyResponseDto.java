package com.familyapp.application.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FamilyResponseDto {
    private FamilyDto family;
    private List<UserDto> users;
    private UserDto user;
    private RoleDto role;
}
