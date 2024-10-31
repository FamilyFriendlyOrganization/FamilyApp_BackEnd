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
@Table(name="Reminder")
@Data
@Builder
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ReminderId")
    private UUID reminderId;

    @Column(name = "Title", nullable = false)
    private String title;
    @Column(name = "Description", nullable = false)
    private String description;
    @Column(name = "RemindDate", nullable = false)
    private LocalDateTime remindDate;
    @Column(name = "Status", nullable = false)
    private Integer status;



    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)  // This defines the foreign key column
    private User user;

    @ManyToOne
    @JoinColumn(name = "FamilyId", nullable = false)  // This defines the foreign key column
    private Family family;
}
