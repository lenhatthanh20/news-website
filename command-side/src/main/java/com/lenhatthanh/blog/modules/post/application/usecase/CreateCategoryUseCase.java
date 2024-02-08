package com.lenhatthanh.blog.modules.post.application.usecase;

import com.lenhatthanh.blog.modules.post.domain.service.CreateCategoryService;
import com.lenhatthanh.blog.modules.post.dto.CategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCategoryUseCase {
    private CreateCategoryService createCategoryService;

    public void execute(CategoryDto categoryDto) {
        createCategoryService.create(categoryDto);
    }
}
