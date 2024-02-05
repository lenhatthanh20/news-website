package com.lenhatthanh.blog.modules.post.domain.service;

import com.lenhatthanh.blog.modules.post.dto.CommentDto;

public interface AddCommentService {
    void add(String postId, CommentDto commentDto);
}
