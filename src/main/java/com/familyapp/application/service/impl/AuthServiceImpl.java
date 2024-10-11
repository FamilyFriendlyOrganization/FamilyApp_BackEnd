/*package com.familyapp.application.service.impl;

import com.familyapp.application.dto.AuthResponse;
import com.familyapp.application.dto.LoginRequest;
import com.familyapp.application.dto.OAuth2Request;
import com.familyapp.application.entity.Account;
import com.familyapp.application.repository.AccountRepository;
import com.familyapp.application.repository.UserRepository;
import com.familyapp.application.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        // Authenticate the user using username or email and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Return basic auth response (e.g., session details)
        return new AuthResponse("Login successful");
    }

    @Override
    public AuthResponse loginWithOAuth2(OAuth2Request oAuth2Request) {
        // Verify the Google OAuth2 token using Google's token verification API
        String googleTokenVerificationUrl = "https://oauth2.googleapis.com/tokeninfo?id_token=" + oAuth2Request.getOauthToken();
        RestTemplate restTemplate = new RestTemplate();

        try {
            Map<String, Object> googleResponse = restTemplate.getForObject(googleTokenVerificationUrl, Map.class);

            if (googleResponse != null && googleResponse.containsKey("email")) {
                String email = (String) googleResponse.get("email");
                String displayName = (String) googleResponse.get("name");
                String uid = (String) googleResponse.get("sub");

                // Find account by email, or create a new account if not found
                Account account = accountRepository.findByEmail(email).orElseGet(() -> {
                    Account newAccount = new Account();
                    newAccount.setEmail(email);
                    newAccount.setDisplayName(displayName);
                    newAccount.setUid(uid);  // Optional: Use UID from Google token if needed
                    return accountRepository.save(newAccount);
                });

                // Authenticate the account in the application context
                Authentication authentication = new UsernamePasswordAuthenticationToken(account.getEmail(), null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // Return basic auth response (e.g., session details)
                return new AuthResponse("Login successful");
            } else {
                throw new RuntimeException("Invalid Google token response.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error verifying OAuth2 token: " + e.getMessage());
        }
    }

    @Override
    public Account register(Account account) {
        // Encrypt the password before saving
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }
}
*/