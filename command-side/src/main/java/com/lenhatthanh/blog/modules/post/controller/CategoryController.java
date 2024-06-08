package com.lenhatthanh.blog.modules.post.controller;

import com.lenhatthanh.blog.modules.post.application.usecase.CreateCategoryUseCase;
import com.lenhatthanh.blog.modules.post.application.usecase.DeleteCategoryUseCase;
import com.lenhatthanh.blog.modules.post.dto.CategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoryController {
    CreateCategoryUseCase createCategoryUseCase;
    DeleteCategoryUseCase deleteCategoryUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody CategoryDto categoryDto) {
        createCategoryUseCase.execute(categoryDto);
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable String id) {
        deleteCategoryUseCase.execute(id);
    }
}
