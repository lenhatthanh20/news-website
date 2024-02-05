package com.lenhatthanh.blog.modules.post.application.usecase;

import com.lenhatthanh.blog.modules.post.domain.service.AddCommentService;
import com.lenhatthanh.blog.modules.post.dto.CommentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddCommentUseCase {
    private AddCommentService addCommentService;

    public void execute(String articleId, CommentDto commentDto) {
        addCommentService.add(articleId, commentDto);
    }
}
