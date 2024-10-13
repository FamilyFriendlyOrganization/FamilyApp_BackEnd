package com.familyapp.application.controller;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping
    public ResponseEntity<AccountDto> register(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(authService.register(accountDto), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<AccountDto> login(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(authService.login(accountDto), HttpStatus.OK);
    }
}
