package com.familyapp.application.service.impl;

import com.familyapp.application.dto.*;
import com.familyapp.application.entity.*;
import com.familyapp.application.exception.ResourceNotFoundException;
import com.familyapp.application.mapper.AccountMapper;
import com.familyapp.application.mapper.FamilyMapper;
import com.familyapp.application.mapper.RoleMapper;
import com.familyapp.application.repository.AccountRepository;
import com.familyapp.application.repository.FamilyRepository;
import com.familyapp.application.repository.InvitationRepository;
import com.familyapp.application.repository.RoleRepository;
import com.familyapp.application.service.FamilyService;
import com.familyapp.application.service.InvitationService;
import com.familyapp.application.service.RoleService;
import com.familyapp.application.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class FamilyServiceImpl implements FamilyService {
    @Autowired
    private FamilyRepository familyRepository;
    private AccountRepository accountRepository;
    private InvitationRepository invitationRepository;
    private RoleRepository roleRepository;
    private UserService userService;
    private RoleService roleService;
    @Override
    public FamilyResponseDto createFamily(FamilyResponseDto familyResponseDto) {
        Account account = accountRepository.findById(familyResponseDto.getFamily().getCreatedAccountId()).orElseThrow(() -> new ResourceNotFoundException("Account not found with given Id: " + familyResponseDto.getFamily().getCreatedAccountId()));
        Family family = FamilyMapper.toEntity(familyResponseDto.getFamily(),account);
        Family savedfamily = familyRepository.save(family);


        RoleDto defaultRole = new RoleDto();
        defaultRole.setFamilyId(savedfamily.getFamilyId());
        defaultRole.setRoleName("Default");
        RoleDto createdRole = roleService.createRole(defaultRole);
        UserDto randomUser = new UserDto();
        randomUser.setName(account.getDisplayName());
        randomUser.setRoleId(createdRole.getRoleId());
        randomUser.setSex(familyResponseDto.getUser().getSex());
        randomUser.setStatus(familyResponseDto.getUser().getStatus());
        randomUser.setBirthday(familyResponseDto.getUser().getBirthday());
        randomUser.setFamilyId(savedfamily.getFamilyId());
        randomUser.setAssignedAccountId(familyResponseDto.getFamily().getCreatedAccountId());
        UserDto savedUserDto = userService.createUser(randomUser);
        familyResponseDto.setFamily(FamilyMapper.toDto(savedfamily));
        familyResponseDto.setUser(savedUserDto);
        familyResponseDto.setRole(createdRole);
        return  familyResponseDto;
    }



    public FamilyDto getFamilybyId(UUID familyId) {
        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found " + familyId));
        return FamilyMapper.toDto(family);
    }

    @Override
    public FamilyDto updateFamily(UUID familyId,FamilyDto updatedFamilyDto) {
        Family family = familyRepository.findById(familyId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Family is not exist with given Id: " + familyId));
        Account createdAccount = accountRepository.findById(updatedFamilyDto.getCreatedAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with given Id: " + updatedFamilyDto.getCreatedAccountId()));
        family.setCreatedAccountId(createdAccount);
        family.setBudget(updatedFamilyDto.getBudget());
        family.setFamilyName(updatedFamilyDto.getFamilyName());
        Family updatedFamily = familyRepository.save(family);
        return FamilyMapper.toDto(updatedFamily);
    }

    @Override
    public List<FamilyDto> getAllFamily() {
        List<Family> families = familyRepository.findAll();
        return families.stream().map((family -> FamilyMapper.toDto(family)))
                .collect(Collectors.toList());
    }


    @Override
    public void deleteFamily(UUID FamilyId) {
        Family Family = familyRepository.findById(FamilyId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Family is not exist with given Id: " + FamilyId));
        familyRepository.deleteById(FamilyId);
    }

    @Override
    public FamilyResponseDto acceptInvitation(String inviteCode, UUID accountId) {
        // Step 1: Validate the invitation using InvitationService
//        if (!invitationService.isInvitationValid(inviteCode)) {
//            throw new IllegalArgumentException("Invitation is expired or already used.");
//        }

        // Step 2: Find the invitation by inviteCode
        Invitation invitation = invitationRepository.findByInviteCode(inviteCode).orElseThrow(() -> new ResourceNotFoundException("Invitation not found for the given code."));

        // Step 3: Find the family associated with the invitation
        Family family = familyRepository.findById(invitation.getFamilyId())
                .orElseThrow(() -> new ResourceNotFoundException("Family not found for the given invitation code."));

        // Step 4: Add the user to the family
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with given Id: " + accountId));

        // Step 5: Retrieve the default role from the database
        Role defaultRole = roleRepository.findByRoleNameAndFamily_FamilyId("Default", family.getFamilyId())
                .orElseThrow(() -> new ResourceNotFoundException("Default role not found for the family"));

        // Step 6: Create the user and associate it with the family and default role
        UserDto userDto = new UserDto();
        userDto.setName(account.getDisplayName());
        userDto.setFamilyId(family.getFamilyId());
        userDto.setBirthday(new Date());
        userDto.setStatus(1);
        userDto.setSex(1);
        userDto.setAssignedAccountId(account.getAccountId());
        userDto.setRoleId(defaultRole.getRoleId()); // Assign the default role to the user

        // Create the user
        UserDto savedUserDto = userService.createUser(userDto);

        // Step 7: Mark the invitation as used
        invitation.setUsed(true);
        invitationRepository.save(invitation);

        // Step 8: Return the updated family response
        FamilyResponseDto familyResponseDto = new FamilyResponseDto();
        familyResponseDto.setFamily(FamilyMapper.toDto(family));
        familyResponseDto.setUser(savedUserDto);

        // Convert the Role entity to RoleDto before setting it in FamilyResponseDto
        RoleDto roleDto = RoleMapper.toDto(defaultRole);  // Assuming you have a RoleMapper to convert Role to RoleDto
        familyResponseDto.setRole(roleDto);  // Set the default role in the response DTO

        return familyResponseDto;
    }

}
