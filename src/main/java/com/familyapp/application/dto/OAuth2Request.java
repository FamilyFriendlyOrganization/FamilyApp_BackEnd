package com.familyapp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data

public class OAuth2Request {
    private UUID id;
    private String email;
    private String name;
}
