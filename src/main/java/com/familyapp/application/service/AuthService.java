package com.familyapp.application.service;

import com.familyapp.application.dto.AccountDto;

public interface AuthService {
    AccountDto register(AccountDto accountDto);
    String login(AccountDto accountDto);
}
