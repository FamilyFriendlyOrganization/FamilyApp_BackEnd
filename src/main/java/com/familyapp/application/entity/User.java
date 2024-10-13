package com.familyapp.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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

    @Column(name = "Name")
    private String name;
    @Column(name = "Sex")
    private byte sex;
    @Column(name = "Birthday")
    private Date birthday;
    @ManyToOne
    @JoinColumn(name = "AssignedAccountId", nullable = false)
    private Account assignedAccountId;

    @ManyToOne
    @JoinColumn(name = "RoleId", nullable = false)  // This defines the foreign key column
    private Role role;

    @ManyToOne
    @JoinColumn(name = "FamilyId", nullable = false)
    private Family family;

}
