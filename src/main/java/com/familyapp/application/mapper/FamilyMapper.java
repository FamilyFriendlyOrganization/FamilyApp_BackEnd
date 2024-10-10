package com.familyapp.application.mapper;

import com.familyapp.application.dto.FamilyDto;
import com.familyapp.application.entity.Account;
import com.familyapp.application.entity.Family;

public class FamilyMapper {
    public static FamilyDto toDto(Family family) {
        if (family == null) {
            return null;
        }
        return new FamilyDto(
                family.getFamilyId(),
                family.getCreatedAccountId().getAccountId(),
                family.getBudget(),
                family.getFamilyName()
        );
    }
    public static Family toEntity(FamilyDto familyDto, Account account) {
        if (familyDto == null) {
            return null;
        }
        Family family = new Family();
        family.setFamilyId(familyDto.getFamilyId());
        family.setCreatedAccountId(account);
        family.setBudget(familyDto.getBudget());
        return family;
    }
}
