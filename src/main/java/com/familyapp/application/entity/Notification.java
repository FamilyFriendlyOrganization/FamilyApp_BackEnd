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
@Table(name="Notification")
@Data
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "NotificationId")
    private UUID notificationId;

    @Column(name="Title", nullable=false)
    private String title;

    @Column(name = "Message", nullable=false)
    private String message;


    @Column(name = "NotificationDateTime", nullable=false)
    private LocalDateTime notificationDateTime;

    @Column(name = "Status", nullable=false)
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)  // This defines the foreign key column
    private User user;

    @ManyToOne
    @JoinColumn(name = "FamilyId", nullable = false)  // This defines the foreign key column
    private Family family;
}
