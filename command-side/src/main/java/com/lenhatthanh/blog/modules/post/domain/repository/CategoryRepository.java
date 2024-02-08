package com.lenhatthanh.blog.modules.post.domain.repository;

import com.lenhatthanh.blog.modules.post.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository {
    void save(Category category);

    Optional<Category> findById(String id);

    void delete(Category category);
}
