package com.lenhatthanh.blog.modules.article.infrastructure.repository;

import com.lenhatthanh.blog.modules.article.infrastructure.repository.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleJpaRepository extends JpaRepository<ArticleEntity, String> {
}