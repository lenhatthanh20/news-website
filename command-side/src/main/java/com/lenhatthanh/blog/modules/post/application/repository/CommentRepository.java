package com.lenhatthanh.blog.modules.post.application.repository;

import com.lenhatthanh.blog.modules.post.domain.entity.Comment;

public interface CommentRepository {
    void save(Comment comment);
}
