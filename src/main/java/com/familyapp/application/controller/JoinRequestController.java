package com.familyapp.application.controller;

import com.familyapp.application.dto.JoinRequestDto;
import com.familyapp.application.service.JoinRequestService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/family")
public class JoinRequestController {

    @Autowired
    private JoinRequestService joinRequestService;

    // Endpoint to get pending join requests
    @GetMapping("/pending-join-requests")
    public ResponseEntity<List<JoinRequestDto>> getPendingJoinRequests() {
        List<JoinRequestDto> pendingRequests = joinRequestService.getPendingJoinRequests();
        return ResponseEntity.ok(pendingRequests);
    }
}
