package com.familyapp.application.controller;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.CategoryDto;
import com.familyapp.application.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/category")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") UUID categoryId){
        CategoryDto categoryDto = categoryService.getCategorybyId(categoryId);
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> categoryDtos = categoryService.getAllCategory();
        return ResponseEntity.ok(categoryDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory( @RequestBody CategoryDto categoryDto,
                                                     @PathVariable("id") UUID categoryId){
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") UUID categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category deleted successfully");
    }
}
