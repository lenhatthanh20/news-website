package com.lenhatthanh.blog.modules.article.domain.repository;

import com.lenhatthanh.blog.modules.article.domain.Article;

import java.util.Optional;

public interface ArticleRepositoryInterface {
    void save(Article article);

    Optional<Article> findById(String id);
}
