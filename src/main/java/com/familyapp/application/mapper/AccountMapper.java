package com.familyapp.application.mapper;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.AccountNoPassDto;
import com.familyapp.application.entity.Account;

public class AccountMapper {
    public static AccountDto toDto(Account account) {
        if (account == null) {
            return null;
        }
        return new AccountDto(
                account.getAccountId(),
                account.getUsername(),
                account.getPassword(),
                account.getEmail(),
                account.getAccountStatus(),
                account.getDisplayName()

        );
    }

    public static Account toEntity(AccountDto accountDto) {
        if (accountDto == null) {
            return null;
        }
        Account account = new Account();
        account.setAccountId(accountDto.getAccountId());
        account.setUsername(accountDto.getUsername());
        account.setPassword(accountDto.getPassword());
        account.setEmail(accountDto.getEmail());
        account.setDisplayName(accountDto.getDisplayName());
        account.setAccountStatus(accountDto.getAccountStatus());
        return account;
    }

    public static AccountNoPassDto toNoPassDto(Account account) {
        if (account == null) {
            return null;
        }
        return new AccountNoPassDto(
                account.getAccountId(),
                account.getUsername(),
                account.getEmail(),
                account.getAccountStatus(),
                account.getDisplayName()
        );
    }
}
