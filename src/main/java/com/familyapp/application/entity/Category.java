package com.familyapp.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Category")
@Data
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "CategoryId")
    private UUID categoryId;

    @Column(name = "CategoryName", nullable = false)
    private String categoryName;

    @Column(name = "CategoryIcon",nullable = false)
    private Integer categoryIcon;

    @Column(name="Status", nullable = false)
    private Integer status;

}
