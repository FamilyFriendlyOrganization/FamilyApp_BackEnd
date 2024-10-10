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

    @Column(name = "CategoryName")
    private String categoryName;

    @Column(name = "CategoryIcon")
    private int categoryIcon;
}
