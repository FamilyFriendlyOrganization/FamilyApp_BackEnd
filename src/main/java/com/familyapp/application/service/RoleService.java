package com.familyapp.application.service;

import com.familyapp.application.dto.RoleDto;
import com.familyapp.application.entity.Family;
import com.familyapp.application.entity.Role;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    RoleDto createRole(RoleDto roleDto);
    RoleDto getRolebyId(UUID roleId);
    RoleDto updateRole(UUID roleId, RoleDto roleDto);
    List<RoleDto> getAllRole();
    void deleteRole(UUID roleId);
}
