package com.familyapp.application.mapper;

import com.familyapp.application.dto.RoleDto;
import com.familyapp.application.entity.Family;
import com.familyapp.application.entity.Role;

public class RoleMapper {
    public static RoleDto toDto(Role role) {
        if (role == null) {
            return null;
        }
        return new RoleDto(
                role.getRoleId(),
                role.getRoleName(),
                role.getFamily().getFamilyId()
        );
    }
    public static Role toEntity(RoleDto roleDto, Family family) {
        if (roleDto == null) {
            return null;
        }
        Role role = new Role();
        role.setRoleId(roleDto.getRoleId());
        role.setRoleName(roleDto.getRoleName());
        role.setFamily(family);
        return role;
    }
}
