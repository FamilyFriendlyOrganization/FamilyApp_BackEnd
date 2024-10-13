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

public class Account extends Password {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "AccountId")
    private UUID accountId;

    @Column(name = "Username")
    private String username;

    @Column(name = "Email")
    private String email;

    @Column(name = "AccountStatus")
    private byte accountStatus;
    @Column(name = "DisplayName")
    private String displayName;

}
