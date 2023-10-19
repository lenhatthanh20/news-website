package com.lenhatthanh.blog.infrastructure.restapi.controller;

import com.lenhatthanh.blog.application.usecase.CreateArticle;
import com.lenhatthanh.blog.dto.ArticleDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
@AllArgsConstructor
public class ArticleController {
    private CreateArticle createArticleUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createArticle(@RequestBody ArticleDto article) {
        createArticleUseCase.execute(article);
    }
}
