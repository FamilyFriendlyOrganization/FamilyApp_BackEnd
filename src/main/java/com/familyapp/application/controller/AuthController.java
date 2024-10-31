package com.familyapp.application.controller;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.AccountNoPassDto;
import com.familyapp.application.dto.JwtAuthResponse;
import com.familyapp.application.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<AccountDto> register(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(authService.register(accountDto), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<AccountNoPassDto> login(@RequestBody AccountDto accountDto) {

        AccountNoPassDto token = authService.login(accountDto);


        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
