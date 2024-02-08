package com.lenhatthanh.blog.modules.post.infra.rest_api;

import com.lenhatthanh.blog.modules.post.application.usecase.AddCommentUseCase;
import com.lenhatthanh.blog.modules.post.application.usecase.CreatePostUseCase;
import com.lenhatthanh.blog.modules.post.dto.PostDto;
import com.lenhatthanh.blog.modules.post.dto.CommentDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
public class PostController {
    private CreatePostUseCase createPostUseCase;
    private AddCommentUseCase addCommentUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostDto post) {
        createPostUseCase.execute(post);
    }

    @RequestMapping("/{postId}/comments")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@PathVariable String postId, @RequestBody CommentDto commentDto) {
        addCommentUseCase.execute(postId, commentDto);
    }
}
