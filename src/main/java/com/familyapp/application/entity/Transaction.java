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

    @Column(name="TransactionName", nullable = false)
    private String transactionName;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "TransactionType", nullable = false)
    private Byte transactionType;

    @Column(name = "Amount", nullable = false)
    private Integer amount;

    @Column(name="Status", nullable = false)
    private Integer status;

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
