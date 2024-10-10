package com.familyapp.application.mapper;

import com.familyapp.application.dto.CategoryDto;
import com.familyapp.application.entity.Category;

public class CategoryMapper {
    public static CategoryDto toDto(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryDto(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getCategoryIcon()
        );
    }
    public static Category toEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryIcon(categoryDto.getCategoryIcon());
        return category;
    }
}
