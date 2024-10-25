package com.familyapp.application.service;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.AccountNoPassDto;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountNoPassDto getAccountById(UUID accountId);

    List<AccountDto> getAllAccounts();

    AccountDto updateAccount(UUID accountId, AccountDto accountDto);

    void deleteAccount(UUID accountId);
}
