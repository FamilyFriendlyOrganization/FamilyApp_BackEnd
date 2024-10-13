package com.familyapp.application.service.impl;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.FamilyDto;
import com.familyapp.application.entity.Account;
import com.familyapp.application.entity.Category;
import com.familyapp.application.entity.Family;
import com.familyapp.application.exception.ResourceNotFoundException;
import com.familyapp.application.mapper.AccountMapper;
import com.familyapp.application.mapper.FamilyMapper;
import com.familyapp.application.repository.AccountRepository;
import com.familyapp.application.repository.FamilyRepository;
import com.familyapp.application.service.FamilyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class FamilyServiceImpl implements FamilyService {
    @Autowired
    private FamilyRepository familyRepository;
    private AccountRepository accountRepository;
    @Override
    public FamilyDto createFamily(FamilyDto familyDto) {
        Account account = accountRepository.findById(familyDto.getCreatedAccountId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account not found with given Id: " + familyDto.getCreatedAccountId()));
        Family family = FamilyMapper.toEntity(familyDto, account);
        Family savedFamily = familyRepository.save(family);
        return FamilyMapper.toDto(savedFamily);
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
}
