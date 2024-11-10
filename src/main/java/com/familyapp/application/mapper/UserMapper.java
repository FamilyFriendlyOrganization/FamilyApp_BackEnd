package com.familyapp.application.mapper;

import com.familyapp.application.dto.UserDto;
import com.familyapp.application.entity.Account;
import com.familyapp.application.entity.Family;
import com.familyapp.application.entity.Role;
import com.familyapp.application.entity.User;

public class UserMapper {
    public static UserDto toDto(User user) {
        if(user == null) {
            return null;
        }
        return new UserDto(
                user.getUserId(),
                user.getAssignedAccountId().getAccountId(),
                user.getRole().getRoleId(),
                user.getFamily().getFamilyId(),
                user.getName(),
                user.getSex(),
                user.getBirthday(),
                user.getStatus()
        );
    }
    public static User toEntity(UserDto userDto, Role role, Family family, Account account) {
        if(userDto == null) {
            return null;
        }
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setAssignedAccountId(account);
        user.setUserId(userDto.getUserId());
        user.setRole(role);
        user.setFamily(family);
        user.setName(userDto.getName());
        user.setSex(userDto.getSex());
        user.setStatus(userDto.getStatus());
        user.setBirthday(userDto.getBirthday());
        return user;
    }
}
