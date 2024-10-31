package com.familyapp.application.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryDto {
    private UUID CategoryId;
    private String CategoryName;
    private Integer CategoryIcon;
    private Integer Status;
}
