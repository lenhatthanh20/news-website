package com.lenhatthanh.blog.modules.post.domain.repository;

import com.lenhatthanh.blog.modules.post.domain.Article;

import java.util.Optional;

public interface ArticleRepository {
    void save(Article article);

    Optional<Article> findById(String id);
}
