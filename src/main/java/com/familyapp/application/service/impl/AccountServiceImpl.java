package com.familyapp.application.service.impl;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.entity.Account;
import com.familyapp.application.exception.ResourceNotFoundException;
import com.familyapp.application.mapper.AccountMapper;
import com.familyapp.application.repository.AccountRepository;
import com.familyapp.application.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.toEntity(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.toDto(savedAccount);
    }

    public AccountDto getAccountById(UUID accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found " + accountId));
        return AccountMapper.toDto(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account -> AccountMapper.toDto(account)))
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto updateAccount(UUID accountId, AccountDto updatedAccountDto) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account is not exist with given Id: " + accountId));

        account.setEmail(updatedAccountDto.getEmail());
        account.setDisplayName(updatedAccountDto.getDisplayName());
        account.setAccountStatus(updatedAccountDto.getAccountStatus());
        Account updatedAccount = accountRepository.save(account);
        return AccountMapper.toDto(updatedAccount);
    }

    @Override
    public void deleteAccount(UUID accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account is not exist with given Id: " + accountId));
        accountRepository.deleteById(accountId);
    }
}
