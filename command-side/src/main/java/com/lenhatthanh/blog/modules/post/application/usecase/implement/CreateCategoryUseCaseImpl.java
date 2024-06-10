package com.lenhatthanh.blog.modules.post.application.usecase.implement;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.post.application.usecase.CreateCategoryUseCase;
import com.lenhatthanh.blog.modules.post.domain.entity.Category;
import com.lenhatthanh.blog.modules.post.domain.service.CreateSlugDomainService;
import com.lenhatthanh.blog.modules.post.domain.valueobject.Title;
import com.lenhatthanh.blog.modules.post.application.exception.CategoryNotFoundException;
import com.lenhatthanh.blog.modules.post.application.repository.CategoryRepository;
import com.lenhatthanh.blog.modules.post.dto.CategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCategoryUseCaseImpl implements CreateCategoryUseCase {
    private final CategoryRepository categoryRepository;
    private final CreateSlugDomainService createSlugDomainService;

    public void execute(CategoryDto categoryDto) {
        if (categoryDto.getParentId() != null) {
            parentCategoryExistOrError(categoryDto.getParentId());
        }

        Category category = Category.create(
                new Title(categoryDto.getTitle()),
                createSlugDomainService.createFromTitle(categoryDto.getTitle()),
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
