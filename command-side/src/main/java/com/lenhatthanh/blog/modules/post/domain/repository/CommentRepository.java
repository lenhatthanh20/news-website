package com.lenhatthanh.blog.modules.post.domain.repository;

import com.lenhatthanh.blog.modules.post.domain.Comment;

public interface CommentRepository {
    void save(Comment comment);
}
