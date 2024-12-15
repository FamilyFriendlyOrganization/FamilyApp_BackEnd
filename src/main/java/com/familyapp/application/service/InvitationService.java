package com.familyapp.application.service;

import com.familyapp.application.dto.FamilyResponseDto;
import com.familyapp.application.dto.InvitationDto;

import java.util.UUID;

public interface InvitationService {
    InvitationDto createInvitation(UUID uuid);
    Boolean isInvitationValid(String inviteCode);

    void rejectInvitation(UUID invitationId);

    FamilyResponseDto approveInvitation(UUID invitationId, UUID accountId);
}
