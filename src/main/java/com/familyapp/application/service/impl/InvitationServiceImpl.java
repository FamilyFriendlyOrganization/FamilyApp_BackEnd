package com.familyapp.application.service.impl;

import com.familyapp.application.dto.InvitationDto;
import com.familyapp.application.entity.Invitation;
import com.familyapp.application.mapper.InvitationMapper;
import com.familyapp.application.repository.FamilyRepository;
import com.familyapp.application.repository.InvitationRepository;
import com.familyapp.application.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;
    @Autowired
    private FamilyRepository familyRepository;

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
}
