package com.lenhatthanh.blog.modules.post.domain.repository;

import com.lenhatthanh.blog.modules.post.domain.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository {
    void save(Category category);
}
