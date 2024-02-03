package com.lenhatthanh.blog.modules.article.domain.repository;

import com.lenhatthanh.blog.modules.article.domain.Article;

import java.util.Optional;

public interface ArticleRepository {
    void save(Article article);

    Optional<Article> findById(String id);
}
