package com.familyapp.application.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Account")
@Data
@Builder

public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "AccountId")
    private UUID accountId;

    @Column(name = "Username", nullable = false, unique = true)
    private String username;

    @Column(name = "Password",nullable = false)
    private String password;

    @Column(name = "Email") //xu ly null sau trong service
    private String email;

    @Column(name = "AccountStatus", nullable = false)
    private Integer accountStatus;
    @Column(name = "DisplayName", nullable = false)
    private String displayName;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return authorities granted to the user (roles, permissions)
        return Collections.emptyList(); // Replace with actual roles if needed
    }
}
