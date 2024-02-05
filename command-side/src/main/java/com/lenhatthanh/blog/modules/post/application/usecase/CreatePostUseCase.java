package com.lenhatthanh.blog.modules.post.application.usecase;

import com.lenhatthanh.blog.modules.post.domain.service.CreatePostService;
import com.lenhatthanh.blog.modules.post.dto.PostDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreatePostUseCase {
    private CreatePostService createPostService;

    public void execute(PostDto postDto) {
        createPostService.create(postDto);
    }
}
