package com.familyapp.application.service.impl;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.UserDto;
import com.familyapp.application.entity.Account;
import com.familyapp.application.entity.Family;
import com.familyapp.application.entity.Role;
import com.familyapp.application.entity.User;
import com.familyapp.application.exception.ResourceNotFoundException;
import com.familyapp.application.mapper.AccountMapper;
import com.familyapp.application.mapper.UserMapper;
import com.familyapp.application.repository.UserRepository;
import com.familyapp.application.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto, Role role, Family family) {
        User user = UserMapper.toEntity(userDto, role, family);
        User savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    public UserDto getUserbyId(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found " + userId));
        return UserMapper.toDto(user);
    }

    @Override
    public UserDto updateUser(UUID userId,UserDto updatedUserDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User is not exist with given Id: " + userId));


        User updatedUser = userRepository.save(user);
        return UserMapper.toDto(updatedUser);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user -> UserMapper.toDto(user)))
                .collect(Collectors.toList());
    }


    @Override
    public void deleteUser(UUID userId) {
        User User = userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User is not exist with given Id: " + userId));
        userRepository.deleteById(userId);
    }
}
