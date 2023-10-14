package com.lenhatthanh.blog.infrastructure.repository;

import com.lenhatthanh.blog.domain.article.Article;
import com.lenhatthanh.blog.domain.repository.ArticleRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ArticleRepository implements ArticleRepositoryInterface {
    private ArticleJpaRepository articleJpaRepository;
    @Override
    public void save(Article article) {
        ArticleEntity articleEntity = ArticleEntity.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .author(AuthorEntity.builder()
                        .id(article.getAuthor().getId())
                        .name(article.getAuthor().getName())
                        .email(article.getAuthor().getEmail())
                        .build())
                .build();

        this.articleJpaRepository.save(articleEntity);
    }
}
