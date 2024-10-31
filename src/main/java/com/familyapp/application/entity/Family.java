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

    @Column(name = "Budget")
    private Integer budget=0;

    @Column(name="Status", nullable = false)
    private Integer status;

    @Column(name = "FamilyName", nullable = false)
    private String familyName;
    @ManyToOne
    @JoinColumn(name = "CreatedAccountId", nullable = false)  // This defines the foreign key column
    private Account createdAccountId;

}
