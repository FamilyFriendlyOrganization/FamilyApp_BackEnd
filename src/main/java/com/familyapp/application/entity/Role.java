package com.familyapp.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Role")
@Data
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "RoleId", nullable = false)
    private UUID roleId;

    @Column(name="RoleName", nullable = false)
    private String roleName;

    @ManyToOne
    @JoinColumn(name = "FamilyId", nullable = false)  // This defines the foreign key column
    private Family family;
}
