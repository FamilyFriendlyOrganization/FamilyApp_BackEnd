package com.familyapp.application.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.entity.Account;
import com.familyapp.application.mapper.AccountMapper;
import com.familyapp.application.repository.AccountRepository;
import com.familyapp.application.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public AccountDto register(AccountDto accountDto) {
        Account account= AccountMapper.toEntity(accountDto);
        account.setPassword(BCrypt.withDefaults().hashToString(12, account.getPassword().toCharArray()));
        Account registeredaccount=  accountRepository.save(account);
        return AccountMapper.toDto(registeredaccount);
    }

    @Override
    public AccountDto login(AccountDto accountDto) {
        Account account = accountRepository.findByUsername(accountDto.getUsername());
        if(account!=null){
            if(BCrypt.verifyer().verify(accountDto.getPassword().toCharArray(), account.getPassword()).verified){
                return AccountMapper.toDto(account);
            }
        }
        return null;
    }
}
