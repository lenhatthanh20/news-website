package com.lenhatthanh.blog.modules.post.application.usecase.implement;

import com.lenhatthanh.blog.modules.post.application.usecase.DeleteCategoryUseCase;
import com.lenhatthanh.blog.modules.post.domain.Category;
import com.lenhatthanh.blog.modules.post.domain.exception.CategoryNotFoundException;
import com.lenhatthanh.blog.modules.post.application.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteCategoryUseCaseImpl implements DeleteCategoryUseCase {
    private final CategoryRepository categoryRepository;

    public void execute(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        categoryRepository.delete(category);
    }
}
