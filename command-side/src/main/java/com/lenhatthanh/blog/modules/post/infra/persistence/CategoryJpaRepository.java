package com.lenhatthanh.blog.modules.post.infra.persistence;

import com.lenhatthanh.blog.modules.post.infra.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, String> {
}
