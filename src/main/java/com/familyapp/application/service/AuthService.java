package com.familyapp.application.service;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.AccountNoPassDto;

import java.util.Map;

public interface AuthService {
    AccountNoPassDto register(AccountDto accountDto);
    AccountNoPassDto login(AccountDto accountDto);
}
