package com.lenhatthanh.blog.modules.article.infrastructure.rest_api;

import com.lenhatthanh.blog.modules.article.application.usecase.AddCommentUseCase;
import com.lenhatthanh.blog.modules.article.application.usecase.CreateArticleUseCase;
import com.lenhatthanh.blog.modules.article.dto.ArticleDto;
import com.lenhatthanh.blog.modules.article.dto.CommentDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
@AllArgsConstructor
public class ArticleController {
    private CreateArticleUseCase createArticleUseCase;
    private AddCommentUseCase addCommentUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createArticle(@RequestBody ArticleDto article) {
        createArticleUseCase.execute(article);
    }

    @RequestMapping("/{articleId}/comments")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@PathVariable String articleId, @RequestBody CommentDto commentDto) {
        addCommentUseCase.execute(articleId, commentDto);
    }
}
