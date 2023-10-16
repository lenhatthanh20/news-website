package com.lenhatthanh.blog.infrastructure.repository;

import com.lenhatthanh.blog.domain.article.Article;
import com.lenhatthanh.blog.domain.repository.ArticleRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ArticleRepository implements ArticleRepositoryInterface {
    public static final String MESSAGE_QUEUE_TOPIC = "article";
    private ArticleJpaRepository articleJpaRepository;
    private KafkaTemplate<String, ArticleDto> kafkaTemplate;

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

        ArticleDto articleDto = new ArticleDto(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                new AuthorDto(article.getAuthor().getId(), article.getAuthor().getName(), article.getAuthor().getEmail())
        );
        this.syncToQuerySide(articleDto);
    }

    private void syncToQuerySide(ArticleDto article) {
        this.kafkaTemplate.send(MESSAGE_QUEUE_TOPIC, article);
    }
}
