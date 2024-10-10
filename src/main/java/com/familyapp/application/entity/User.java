package com.familyapp.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
@Entity
@Data
@Builder
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "UserId")
    private UUID userId;

    @Column(name = "AssignedAccountId")
    private UUID assignedAccountId;
    @Column(name = "FamilyId")
    private UUID familyId;
    @Column(name = "Name")
    private String name;
    @Column(name = "Sex")
    private byte sex;
    @Column(name = "Birthday")
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "AccountId", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "RoleId", nullable = false)  // This defines the foreign key column
    private Role role;
}
