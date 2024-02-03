package com.lenhatthanh.blog.modules.article.application.usecase;

import com.lenhatthanh.blog.modules.article.domain.service.AddCommentService;
import com.lenhatthanh.blog.modules.article.dto.CommentDto;
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
