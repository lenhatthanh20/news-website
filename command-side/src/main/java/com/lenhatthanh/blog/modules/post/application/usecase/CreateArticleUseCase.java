package com.lenhatthanh.blog.modules.post.application.usecase;

import com.lenhatthanh.blog.modules.post.domain.service.CreateArticleService;
import com.lenhatthanh.blog.modules.post.dto.ArticleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateArticleUseCase {
    private CreateArticleService createArticleService;

    public void execute(ArticleDto articleRequest) {
        createArticleService.create(articleRequest);
    }
}
