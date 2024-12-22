package com.familyapp.application.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.AccountNoPassDto;
import com.familyapp.application.entity.Account;
import com.familyapp.application.mapper.AccountMapper;
import com.familyapp.application.repository.AccountRepository;
import com.familyapp.application.security.JwtAuthenticationEntryPoint;
import com.familyapp.application.security.JwtTokenProvider;
import com.familyapp.application.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private AccountRepository accountRepository;
    private JwtTokenProvider jwtTokenProvider;

    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    @Override
    public AccountNoPassDto register(AccountDto accountDto) {
        Account account = AccountMapper.toEntity(accountDto);
        account.setPassword(passwordEncoder.encode(account.getPassword())); // Use PasswordEncoder to hash password
        Account registeredAccount = accountRepository.save(account);
        return AccountMapper.toNoPassDto(registeredAccount);
    }

    @Override
    public AccountNoPassDto login(AccountDto accountDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            accountDto.getUsername(),
                            accountDto.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            AccountNoPassDto returnedAccount = AccountMapper.toNoPassDto(accountRepository.findByUsername(accountDto.getUsername()));
            returnedAccount.setToken(jwtTokenProvider.generateToken(authentication));
            return returnedAccount;
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid username or password", e);
        }
    }
}
