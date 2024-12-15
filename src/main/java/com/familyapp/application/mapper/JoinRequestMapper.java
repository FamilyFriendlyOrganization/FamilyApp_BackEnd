package com.familyapp.application.mapper;

import com.familyapp.application.dto.JoinRequestDto;
import com.familyapp.application.entity.JoinRequest;
import com.familyapp.application.entity.Account;
import com.familyapp.application.entity.Family;

public class JoinRequestMapper {

    // Convert JoinRequest entity to JoinRequestDto
    public static JoinRequestDto toDto(JoinRequest joinRequest) {
        if (joinRequest == null) {
            return null;
        }

        return JoinRequestDto.builder()
                .joinRequestId(joinRequest.getJoinRequestId())
                .accountId(joinRequest.getAccount().getAccountId()) // Assuming Account has getId() method
                .familyId(joinRequest.getFamily().getFamilyId())   // Assuming Family has getId() method
                .status(joinRequest.getStatus().name()) // Convert enum to String
                .joinDate(joinRequest.getRequestDate())
                .build();
    }

    // Convert JoinRequestDto to JoinRequest entity
    public static JoinRequest toEntity(JoinRequestDto joinRequestDto) {
        if (joinRequestDto == null) {
            return null;
        }

        JoinRequest joinRequest = new JoinRequest();
        joinRequest.setJoinRequestId(joinRequestDto.getJoinRequestId());

        // Assuming Account and Family are entities and need to be set properly
        Account account = new Account();
        account.setAccountId(joinRequestDto.getAccountId());  // Assuming setId() method exists
        joinRequest.setAccount(account);

        Family family = new Family();
        family.setFamilyId(joinRequestDto.getFamilyId());  // Assuming setId() method exists
        joinRequest.setFamily(family);

        // Convert status from String to enum
        JoinRequest.JoinRequestStatus status = JoinRequest.JoinRequestStatus.valueOf(joinRequestDto.getStatus());
        joinRequest.setStatus(status);

        joinRequest.setRequestDate(joinRequestDto.getJoinDate());

        return joinRequest;
    }
}
