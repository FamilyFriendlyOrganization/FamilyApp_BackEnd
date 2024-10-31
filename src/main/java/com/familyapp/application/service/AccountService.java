package com.familyapp.application.service;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.AccountNoPassDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface AccountService extends UserDetailsService {
    AccountDto createAccount(AccountDto accountDto);

    //AccountDto getAccountByUsername(String username);
    AccountNoPassDto getAccountById(UUID accountId);

    List<AccountDto> getAllAccounts();

    AccountDto updateAccount(UUID accountId, AccountDto accountDto);

    void deleteAccount(UUID accountId);
}
