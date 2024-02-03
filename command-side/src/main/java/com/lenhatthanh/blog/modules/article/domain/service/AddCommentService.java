package com.lenhatthanh.blog.modules.article.domain.service;

import com.lenhatthanh.blog.modules.article.dto.CommentDto;

public interface AddCommentService {
    void add(String articleId, CommentDto commentDto);
}
