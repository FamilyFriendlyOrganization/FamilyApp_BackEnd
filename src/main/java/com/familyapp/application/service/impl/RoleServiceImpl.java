package com.familyapp.application.service.impl;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.RoleDto;
import com.familyapp.application.entity.Account;
import com.familyapp.application.entity.Category;
import com.familyapp.application.entity.Family;
import com.familyapp.application.entity.Role;
import com.familyapp.application.exception.ResourceNotFoundException;
import com.familyapp.application.mapper.AccountMapper;
import com.familyapp.application.mapper.RoleMapper;
import com.familyapp.application.repository.FamilyRepository;
import com.familyapp.application.repository.RoleRepository;
import com.familyapp.application.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    private FamilyRepository familyRepository;
    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Family family = familyRepository.findById(roleDto.getFamilyId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account not found with given Id: " + roleDto.getFamilyId()));
        Role Role = RoleMapper.toEntity(roleDto,family);
        Role savedRole = roleRepository.save(Role);
        return RoleMapper.toDto(savedRole);
    }

    public RoleDto getRolebyId(UUID roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found " + roleId));
        return RoleMapper.toDto(role);
    }

    @Override
    public RoleDto updateRole(UUID roleId,RoleDto updatedRoleDto) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Role is not exist with given Id: " + roleId));

        role.setRoleName(updatedRoleDto.getRoleName());
        Family family = familyRepository.findById(updatedRoleDto.getFamilyId())
                .orElseThrow(() -> new ResourceNotFoundException("Family does not exist with the given Id: " + updatedRoleDto.getFamilyId()));
        role.setFamily(family);
        Role updatedRole = roleRepository.save(role);
        return RoleMapper.toDto(updatedRole);
    }

    @Override
    public List<RoleDto> getAllRole() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map((role -> RoleMapper.toDto(role)))
                .collect(Collectors.toList());
    }


    @Override
    public void deleteRole(UUID roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Role is not exist with given Id: " + roleId));
        roleRepository.deleteById(roleId);
    }
}
