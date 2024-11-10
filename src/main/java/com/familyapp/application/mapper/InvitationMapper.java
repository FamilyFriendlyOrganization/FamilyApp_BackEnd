package com.familyapp.application.mapper;

import com.familyapp.application.dto.InvitationDto;
import com.familyapp.application.entity.Invitation;

public class InvitationMapper {

    // Convert Invitation entity to InvitationDto
    public static InvitationDto toDto(Invitation invitation) {
        if (invitation == null) {
            return null;
        }

        return InvitationDto.builder()
                .inviteId(invitation.getId())
                .inviteCode(invitation.getInviteCode())
                .familyId(invitation.getFamilyId())
                .expirationTime(invitation.getExpirationTime())
                .isUsed(invitation.isUsed())
                .build();
    }

    // Convert InvitationDto to Invitation entity
    public static Invitation toEntity(InvitationDto invitationDto) {
        if (invitationDto == null) {
            return null;
        }

        Invitation invitation = new Invitation();
        invitation.setId(invitationDto.getInviteId());
        invitation.setInviteCode(invitationDto.getInviteCode());
        invitation.setFamilyId(invitationDto.getFamilyId());
        invitation.setExpirationTime(invitationDto.getExpirationTime());
        invitation.setUsed(invitationDto.isUsed());

        return invitation;
    }
}
