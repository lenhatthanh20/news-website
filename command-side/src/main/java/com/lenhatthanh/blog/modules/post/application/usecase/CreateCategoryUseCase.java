package com.lenhatthanh.blog.modules.post.application.usecase;

import com.lenhatthanh.blog.modules.post.dto.CategoryDto;

public interface CreateCategoryUseCase {
    public void execute(CategoryDto categoryDto);
}
