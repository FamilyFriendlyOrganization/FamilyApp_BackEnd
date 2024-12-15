package com.familyapp.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "JoinRequest")
public class JoinRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID joinRequestId;

    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "familyId", nullable = false)
    private Family family;

    public enum JoinRequestStatus {
        PENDING,
        APPROVED,
        REJECTED
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private JoinRequestStatus status;

    // Additional fields like requestDate, etc.
    private LocalDateTime requestDate;
}
