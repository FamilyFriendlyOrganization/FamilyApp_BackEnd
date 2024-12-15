package com.familyapp.application.controller;

import com.familyapp.application.dto.FamilyResponseDto;
import com.familyapp.application.dto.InvitationDto;
import com.familyapp.application.entity.Invitation;
import com.familyapp.application.exception.ResourceNotFoundException;
import com.familyapp.application.service.InvitationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Controller
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/api/invite")

public class InvitationController {
    @Autowired
    private InvitationService invitationService;

    @PostMapping("/create")
    public ResponseEntity<InvitationDto> createInvitation(@RequestParam UUID familyId) {
        InvitationDto invitation = invitationService.createInvitation(familyId);
        return ResponseEntity.ok(invitation);
    }

    @PutMapping("/approve")
    public ResponseEntity<FamilyResponseDto> approveInvitation(
            @RequestParam UUID invitationId,
            @RequestParam UUID accountId) {
        try {
            // Call the service to approve the invitation
            FamilyResponseDto response = invitationService.approveInvitation(invitationId, accountId);

            // Return the response as a 200 OK with the family details
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            // Return a 404 error if a resource is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            // Return a 400 error if the input is invalid
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            // Return a 500 error for any other unexpected exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PutMapping("/reject")
    public ResponseEntity<String> rejectInvitation(@RequestParam UUID invitationId) {
        try {
            invitationService.rejectInvitation(invitationId);
            return ResponseEntity.ok("Invitation rejected.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
