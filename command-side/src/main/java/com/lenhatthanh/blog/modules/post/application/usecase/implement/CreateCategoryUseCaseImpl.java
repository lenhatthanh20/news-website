package com.lenhatthanh.blog.modules.post.application.usecase.implement;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.post.application.usecase.CreateCategoryUseCase;
import com.lenhatthanh.blog.modules.post.domain.Category;
import com.lenhatthanh.blog.modules.post.domain.Title;
import com.lenhatthanh.blog.modules.post.domain.exception.CategoryNotFoundException;
import com.lenhatthanh.blog.modules.post.application.repository.CategoryRepository;
import com.lenhatthanh.blog.modules.post.domain.service.CreateSlugFromTitleService;
import com.lenhatthanh.blog.modules.post.dto.CategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCategoryUseCaseImpl implements CreateCategoryUseCase {
    private final CategoryRepository categoryRepository;
    private final CreateSlugFromTitleService createSlugFromTitleService;

    public void execute(CategoryDto categoryDto) {
        if (categoryDto.getParentId() != null) {
            this.parentCategoryExistOrError(categoryDto.getParentId());
        }

        Category category = Category.create(
                new Title(categoryDto.getTitle()),
                createSlugFromTitleService.create(categoryDto.getTitle()),
                categoryDto.getParentId() == null ? null : new Id(categoryDto.getParentId())
        );

        // TODO: Business logic: category slug must be unique
        // TODO: Check user and user roles
        categoryRepository.save(category);
    }

    private void parentCategoryExistOrError(String parentId) {
        categoryRepository.findById(parentId).orElseThrow(CategoryNotFoundException::new);
    }
}
