package com.familyapp.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Family")
@Data
@Builder
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "FamilyId")
    private UUID familyId;

    @Column(name="CreatedAccountId")
    private UUID createdAccountId;

    @Column(name = "Budget")
    private int budget;

    @Column(name = "FamilyName")
    private String familyName;
    @ManyToOne
    @JoinColumn(name = "AccountId", nullable = false)  // This defines the foreign key column
    private Account account;
}
