package com.lenhatthanh.blog.modules.post.application.usecase;

import com.lenhatthanh.blog.modules.post.dto.PostDto;

public interface CreatePostUseCase {
    public void execute(PostDto postDto);
}
