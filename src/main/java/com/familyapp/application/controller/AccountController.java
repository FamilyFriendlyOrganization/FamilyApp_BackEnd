package com.familyapp.application.controller;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.AccountNoPassDto;
import com.familyapp.application.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountNoPassDto> getAccountById(@PathVariable("id") UUID accountId){
        AccountNoPassDto accountNoPassDto = accountService.getAccountById(accountId);
        return ResponseEntity.ok(accountNoPassDto);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accountDtos = accountService.getAllAccounts();
        return ResponseEntity.ok(accountDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<AccountDto> updateAccount( @RequestBody AccountDto accountDto,
                                                    @PathVariable("id") UUID accountId){
        return ResponseEntity.ok(accountService.updateAccount(accountId, accountDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") UUID accountId){
        accountService.deleteAccount(accountId);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
