package com.familyapp.application.service;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.RoleDto;
import com.familyapp.application.dto.UserDto;
import com.familyapp.application.entity.Family;
import com.familyapp.application.entity.Role;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto createUser(UserDto userDto, Role role, Family family);
    UserDto getUserbyId(UUID userId);
    UserDto updateUser(UUID userId, UserDto userDto);
    List<UserDto> getAllUser();
    void deleteUser(UUID userId);
}
