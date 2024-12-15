package com.familyapp.application.service;

import com.familyapp.application.dto.FamilyDto;
import com.familyapp.application.dto.FamilyResponseDto;
import com.familyapp.application.entity.Account;
import com.familyapp.application.entity.Family;

import java.util.List;
import java.util.UUID;

public interface FamilyService {
    FamilyResponseDto createFamily(FamilyResponseDto familyDto);
    FamilyResponseDto getFamilybyId(UUID familyId);
    FamilyDto updateFamily(UUID FamilyId, FamilyDto familyDto);
    List<FamilyDto> getAllFamily();
    void deleteFamily(UUID familyId);
    FamilyResponseDto acceptInvitation(String inviteCode, UUID accountId);

    void joinFamily(String inviteCode, UUID accountId);
}
