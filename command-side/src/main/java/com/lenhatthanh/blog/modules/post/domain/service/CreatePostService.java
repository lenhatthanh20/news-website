package com.lenhatthanh.blog.modules.post.domain.service;

import com.lenhatthanh.blog.modules.post.dto.PostDto;

public interface CreatePostService {
    void create(PostDto postDto);
}
