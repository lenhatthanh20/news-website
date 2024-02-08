package com.lenhatthanh.blog.modules.post.infra.rest_api;

import com.lenhatthanh.blog.modules.post.application.usecase.CreateCategoryUseCase;
import com.lenhatthanh.blog.modules.post.dto.CategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoryController {
    CreateCategoryUseCase createCategoryUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody CategoryDto post) {
        createCategoryUseCase.execute(post);
    }
}

