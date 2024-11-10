package com.familyapp.application.controller;

import com.familyapp.application.dto.InvitationDto;
import com.familyapp.application.entity.Invitation;
import com.familyapp.application.service.InvitationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/accept")
    public ResponseEntity<Boolean> acceptInvitation(@RequestParam String inviteCode) {
        try {
            Boolean valid = invitationService.isInvitationValid(inviteCode);
            return ResponseEntity.ok(valid); // Return the updated InvitationDto
        } catch (IllegalArgumentException e) {
            // Handle the case where the invitation code is invalid or expired
            return ResponseEntity.badRequest().body(null); // Return bad request with no content
        }
    }

}
