package com.lenhatthanh.blog.modules.post.domain.repository;

import com.lenhatthanh.blog.modules.post.domain.Category;
import com.lenhatthanh.blog.modules.post.infra.repository.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository {
    void save(Category category);

    Optional<CategoryEntity> findById(String id);
}
