package com.lenhatthanh.blog.modules.post.domain.service;

import com.lenhatthanh.blog.modules.post.domain.Category;
import com.lenhatthanh.blog.modules.post.domain.exception.CategoryNotFoundException;
import com.lenhatthanh.blog.modules.post.domain.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteCategoryServiceImpl implements DeleteCategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public void delete(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        categoryRepository.delete(category);
    }
}
