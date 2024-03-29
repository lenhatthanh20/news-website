package com.lenhatthanh.blog.modules.post.domain.service;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.post.domain.Category;
import com.lenhatthanh.blog.modules.post.domain.Title;
import com.lenhatthanh.blog.modules.post.domain.exception.CategoryNotFoundException;
import com.lenhatthanh.blog.modules.post.domain.repository.CategoryRepository;
import com.lenhatthanh.blog.modules.post.dto.CategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCategoryServiceImpl implements CreateCategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public void create(CategoryDto categoryDto) {
        Category category = Category.create(new Title(categoryDto.getTitle()));
        if (categoryDto.getParentId() != null) {
            this.parentCategoryExistOrError(categoryDto.getParentId());
            Id parentId = new Id(categoryDto.getParentId());
            category.setParentId(parentId);
        }

        // TODO: Business logic: category slug must be unique

        categoryRepository.save(category);
    }

    private void parentCategoryExistOrError(String parentId) {
        categoryRepository.findById(parentId).orElseThrow(CategoryNotFoundException::new);
    }
}
