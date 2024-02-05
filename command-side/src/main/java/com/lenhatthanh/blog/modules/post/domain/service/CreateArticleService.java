package com.lenhatthanh.blog.modules.post.domain.service;

import com.lenhatthanh.blog.modules.post.dto.ArticleDto;

public interface CreateArticleService {
    void create(ArticleDto articleDto);
}
