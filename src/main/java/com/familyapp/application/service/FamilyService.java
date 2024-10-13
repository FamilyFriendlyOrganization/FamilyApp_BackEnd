package com.familyapp.application.service;

import com.familyapp.application.dto.FamilyDto;
import com.familyapp.application.entity.Account;

import java.util.List;
import java.util.UUID;

public interface FamilyService {
    FamilyDto createFamily(FamilyDto familyDto, Account account);
    FamilyDto getFamilybyId(UUID familyId);
    FamilyDto updateFamily(UUID FamilyId, FamilyDto familyDto);
    List<FamilyDto> getAllFamily();
    void deleteFamily(UUID familyId);
}
