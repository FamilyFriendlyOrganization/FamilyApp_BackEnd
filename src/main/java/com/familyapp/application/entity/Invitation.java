package com.familyapp.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Invitation")
@Data
@Builder
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String inviteCode; // Mã mời
    private UUID familyId; // ID của gia đình
    private LocalDateTime expirationTime; // Thời gian hết hạn
    private boolean isUsed; // Trạng thái đã sử dụng hay chưa

}
