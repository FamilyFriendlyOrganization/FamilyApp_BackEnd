package com.familyapp.application.entity;

import jakarta.persistence.*;
import lombok.*;

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

public class Account {
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
    private Byte accountStatus;
    @Column(name = "DisplayName", nullable = false)
    private String displayName;

}
