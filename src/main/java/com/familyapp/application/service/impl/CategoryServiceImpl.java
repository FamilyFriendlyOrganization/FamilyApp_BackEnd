package com.familyapp.application.service.impl;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.CategoryDto;
import com.familyapp.application.entity.Account;
import com.familyapp.application.entity.Category;
import com.familyapp.application.exception.ResourceNotFoundException;
import com.familyapp.application.mapper.AccountMapper;
import com.familyapp.application.mapper.CategoryMapper;
import com.familyapp.application.repository.CategoryRepository;
import com.familyapp.application.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.toEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.toDto(savedCategory);
    }

    public CategoryDto getCategorybyId(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found " + categoryId));
        return CategoryMapper.toDto(category);
    }

    @Override
    public CategoryDto updateCategory(UUID categoryId,CategoryDto updatedcategoryDto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Category is not exist with given Id: " + categoryId));

        category.setCategoryName(updatedcategoryDto.getCategoryName());
        category.setCategoryIcon(updatedcategoryDto.getCategoryIcon());
        Category updatedCategory = categoryRepository.save(category);
        return CategoryMapper.toDto(updatedCategory);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map((category -> CategoryMapper.toDto(category)))
                .collect(Collectors.toList());
    }


    @Override
    public void deleteCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Category is not exist with given Id: " + categoryId));
        categoryRepository.deleteById(categoryId);
    }
}
