package com.lenhatthanh.blog.modules.post.infrastructure.repository;

import com.lenhatthanh.blog.modules.post.infrastructure.repository.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleJpaRepository extends JpaRepository<ArticleEntity, String> {
}