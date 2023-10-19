package com.lenhatthanh.blog.application.usecase;

import com.lenhatthanh.blog.application.exception.AuthorNotFoundException;
import com.lenhatthanh.blog.domain.Article;
import com.lenhatthanh.blog.domain.Author;
import com.lenhatthanh.blog.domain.repository.ArticleRepositoryInterface;
import com.lenhatthanh.blog.domain.repository.AuthorRepositoryInterface;
import com.lenhatthanh.blog.dto.ArticleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CreateArticle {
    private ArticleRepositoryInterface articleRepository;
    private AuthorRepositoryInterface authorRepository;

    public void execute(ArticleDto articleRequest) {
        Optional<Author> author = authorRepository.findById(articleRequest.getAuthorId());
        if (author.isEmpty()) {
            throw new AuthorNotFoundException("APPLICATION-ERROR-0001");
        }

        Article article = new Article(
                UUID.randomUUID().toString(),
                articleRequest.getTitle(),
                articleRequest.getContent(),
                author.get().getId(),
                articleRequest.getSummary(),
                articleRequest.getThumbnail(),
                articleRequest.getSlug()
        );

        articleRepository.save(article);
    }
}
