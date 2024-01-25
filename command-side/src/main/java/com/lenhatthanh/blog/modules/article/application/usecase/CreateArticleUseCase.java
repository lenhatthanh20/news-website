package com.lenhatthanh.blog.modules.article.application.usecase;

import com.lenhatthanh.blog.modules.article.domain.service.CreateArticleServiceInterface;
import com.lenhatthanh.blog.modules.article.dto.ArticleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateArticleUseCase {
    private CreateArticleServiceInterface createArticleService;

    public void execute(ArticleDto articleRequest) {
        createArticleService.create(articleRequest);
    }
}
