package com.lenhatthanh.blog.modules.post.application.usecase;

import com.lenhatthanh.blog.modules.post.dto.CommentDto;

public interface AddCommentUseCase {
    public void execute(String postId, CommentDto commentDto);
}
