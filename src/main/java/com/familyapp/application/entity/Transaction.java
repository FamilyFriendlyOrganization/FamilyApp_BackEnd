package com.familyapp.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Transaction")
@Data
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "TransactionId")
    private UUID transactionId;

    @Column(name="TransactionName")
    private String transactionName;

    @Column(name = "Description")
    private String description;

    @Column(name = "TransactionType")
    private byte transactionType;

    @Column(name = "Amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)  // This defines the foreign key column
    private User user;

    @ManyToOne
    @JoinColumn(name = "FamilyId", nullable = false)  // This defines the foreign key column
    private Family family;

    @ManyToOne
    @JoinColumn(name = "CategoryId", nullable = false)  // This defines the foreign key column
    private Category category;
}
