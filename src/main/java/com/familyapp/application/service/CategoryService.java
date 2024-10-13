package com.familyapp.application.service;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.CategoryDto;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto getCategorybyId(UUID categoryId);
    CategoryDto updateCategory(UUID categoryId, CategoryDto categoryDto);
    List<CategoryDto> getAllCategory();
    void deleteCategory(UUID categoryId);
}
