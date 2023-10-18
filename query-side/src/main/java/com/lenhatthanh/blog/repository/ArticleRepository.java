package com.lenhatthanh.blog.repository;

import com.lenhatthanh.blog.model.Article;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface ArticleRepository extends RedisDocumentRepository<Article, String> {
}
