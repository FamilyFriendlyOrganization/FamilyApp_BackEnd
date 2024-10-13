package com.familyapp.application.service;

import com.familyapp.application.dto.AccountDto;

public interface AuthService {
    AccountDto register(AccountDto accountDto);
    AccountDto login(AccountDto accountDto);
}
