package com.familyapp.application.service.impl;

import com.familyapp.application.dto.FamilyResponseDto;
import com.familyapp.application.dto.InvitationDto;
import com.familyapp.application.dto.RoleDto;
import com.familyapp.application.dto.UserDto;
import com.familyapp.application.entity.Account;
import com.familyapp.application.entity.Family;
import com.familyapp.application.entity.Invitation;
import com.familyapp.application.entity.Role;
import com.familyapp.application.exception.ResourceNotFoundException;
import com.familyapp.application.mapper.FamilyMapper;
import com.familyapp.application.mapper.InvitationMapper;
import com.familyapp.application.mapper.RoleMapper;
import com.familyapp.application.repository.AccountRepository;
import com.familyapp.application.repository.FamilyRepository;
import com.familyapp.application.repository.InvitationRepository;
import com.familyapp.application.repository.RoleRepository;
import com.familyapp.application.service.InvitationService;
import com.familyapp.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;
    @Autowired
    private FamilyRepository familyRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;
    @Override
    public InvitationDto createInvitation(UUID familyId) {
        // Check if the family exists
        if (!familyRepository.existsById(familyId)) {
            throw new IllegalArgumentException("Family with ID " + familyId + " does not exist.");
        }

        // Create a new invitation
        Invitation invitation = new Invitation();
        invitation.setInviteCode(UUID.randomUUID().toString()); // Generate a random invite code
        invitation.setFamilyId(familyId); // Set the family ID
        invitation.setExpirationTime(LocalDateTime.now().plusDays(7)); // Expiration after 7 days
        invitation.setUsed(false);

        // Save the invitation and convert to DTO
        invitation = invitationRepository.save(invitation);
        return InvitationMapper.toDto(invitation); // Map to DTO before returning
    }

    @Override
    public Boolean isInvitationValid(String inviteCode) {
        Optional<Invitation> invitationOpt = invitationRepository.findByInviteCode(inviteCode);
        return invitationOpt.isPresent() && !invitationOpt.get().isUsed() && invitationOpt.get().getExpirationTime().isAfter(LocalDateTime.now());
    }

    @Override
    public void rejectInvitation(UUID invitationId) {
        // Step 1: Find the invitation by ID
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new IllegalArgumentException("Invitation not found with ID: " + invitationId));

        // Step 2: Mark the invitation as rejected
        invitation.setUsed(true); // Assuming rejected invitations are marked as 'used' to avoid further use
        invitation.setExpirationTime(LocalDateTime.now()); // Optionally set expiration time to current time to invalidate the invitation
        invitationRepository.save(invitation); // Save the updated invitation

        // Optionally, you can perform other actions like notifying the family or user if needed.
    }

    @Override
    public FamilyResponseDto approveInvitation(UUID invitationId, UUID accountId) {
        // Step 1: Find the invitation by invitationId
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new ResourceNotFoundException("Invitation not found for the given ID."));

        // Step 2: Validate that the invitation has not already been approved or used
        if (invitation.isUsed()) {
            throw new IllegalArgumentException("Invitation has already been approved or used.");
        }

        // Step 3: Find the family associated with the invitation
        Family family = familyRepository.findById(invitation.getFamilyId())
                .orElseThrow(() -> new ResourceNotFoundException("Family not found for the given invitation ID."));

        // Step 4: Find the account to be added to the family
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with the given ID: " + accountId));

        // Step 5: Retrieve the default role for the family
        Role defaultRole = roleRepository.findByRoleNameAndFamily_FamilyId("Default", family.getFamilyId())
                .orElseThrow(() -> new ResourceNotFoundException("Default role not found for the family"));

        // Step 6: Create the user and associate it with the family and default role
        UserDto userDto = new UserDto();
        userDto.setName(account.getDisplayName());
        userDto.setFamilyId(family.getFamilyId());
        userDto.setBirthday(new Date()); // Example: Setting the birthday to the current date
        userDto.setStatus(1); // Active status
        userDto.setSex(1); // Example: Default sex value (adjust as needed)
        userDto.setAssignedAccountId(account.getAccountId());
        userDto.setRoleId(defaultRole.getRoleId()); // Assign the default role to the user

        // Create the user in the system
        UserDto savedUserDto = userService.createUser(userDto);

        // Step 7: Mark the invitation as approved and used
        invitation.setUsed(true);
        invitationRepository.save(invitation);

        // Step 8: Return the updated family response
        FamilyResponseDto familyResponseDto = new FamilyResponseDto();
        familyResponseDto.setFamily(FamilyMapper.toDto(family));
        familyResponseDto.setUser(savedUserDto);

        // Convert the Role entity to RoleDto before setting it in the response DTO
        RoleDto roleDto = RoleMapper.toDto(defaultRole); // Assuming you have a RoleMapper for conversion
        familyResponseDto.setRole(roleDto); // Set the default role in the response DTO

        return familyResponseDto;
    }


}
