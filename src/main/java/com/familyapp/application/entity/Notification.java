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
@Entity
@Table(name="Notification")
@Data
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "NotificationId")
    private UUID notificationId;

    @Column(name="Title")
    private String title;

    @Column(name = "Message")
    private String message;


    @Column(name = "NotificationDateTime")
    private LocalDateTime notificationDateTime;

    @Column(name = "Status")
    private byte status;

    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)  // This defines the foreign key column
    private User user;

    @ManyToOne
    @JoinColumn(name = "FamilyId", nullable = false)  // This defines the foreign key column
    private Family family;
}
