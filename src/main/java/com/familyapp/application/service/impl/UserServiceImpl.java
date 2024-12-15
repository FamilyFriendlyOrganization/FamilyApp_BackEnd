package com.familyapp.application.service.impl;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.UserDto;
import com.familyapp.application.entity.*;
import com.familyapp.application.exception.ResourceNotFoundException;
import com.familyapp.application.mapper.AccountMapper;
import com.familyapp.application.mapper.UserMapper;
import com.familyapp.application.repository.AccountRepository;
import com.familyapp.application.repository.FamilyRepository;
import com.familyapp.application.repository.RoleRepository;
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
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private FamilyRepository familyRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        Role role = roleRepository.findById(userDto.getRoleId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account not found with given Id: " + userDto.getRoleId()));
        Family family = familyRepository.findById(userDto.getFamilyId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account not found with given Id: " + userDto.getFamilyId()));
        Account account = accountRepository.findById(userDto.getAssignedAccountId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account not found with given Id: " + userDto.getAssignedAccountId()));

        User user = UserMapper.toEntity(userDto, role, family,account);
        User savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    @Override

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

    @Override
    public boolean isUserInFamily(UUID accountId, UUID familyId) {
        // Check if a user with the given accountId is associated with the given familyId
        return userRepository.existsByAssignedAccountId_AccountIdAndFamily_FamilyId(accountId, familyId);
    }

    @Override
    public List<UserDto> getUsersByFamilyId(UUID familyId) {
        List<User> users = userRepository.findAllByFamily_FamilyId(familyId);
        return users.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }



}
