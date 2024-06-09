package com.lenhatthanh.blog.modules.post.domain.service;

import com.lenhatthanh.blog.modules.post.domain.entity.Post;
import com.lenhatthanh.blog.modules.post.dto.PostDto;

public interface PostDomainService {
    Post createNewPost(PostDto postDto);
}
