package com.familyapp.application.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleDto {
    private UUID roleId;
    private String roleName;
    private UUID familyId;
}
