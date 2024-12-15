package com.familyapp.application.service;

import com.familyapp.application.dto.JoinRequestDto;

import java.util.List;

public interface JoinRequestService {
    List<JoinRequestDto> getPendingJoinRequests();
}
