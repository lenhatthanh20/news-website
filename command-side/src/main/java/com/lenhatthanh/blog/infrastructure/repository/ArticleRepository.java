package com.lenhatthanh.blog.infrastructure.repository;

import com.lenhatthanh.blog.domain.Article;
import com.lenhatthanh.blog.domain.repository.ArticleRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class ArticleRepository implements ArticleRepositoryInterface {
    public static final String MESSAGE_QUEUE_TOPIC = "articles";
    private ArticleJpaRepository articleJpaRepository;
    private KafkaTemplate<String, ArticleEntity> kafkaTemplate;

    @Override
    public void save(Article article) {
        AuthorEntity author = new AuthorEntity();
        author.setId(article.getAuthorId());

        LocalDateTime createAt = LocalDateTime.now();
        ArticleEntity articleEntity = ArticleEntity.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .author(author)
                .summary(article.getSummary())
                .thumbnail(article.getThumbnail())
                .slug(article.getSlug().getValue())
                .publishedAt(createAt)
                .createdAt(createAt)
                .updatedAt(createAt)
                .build();

        this.articleJpaRepository.save(articleEntity);
        this.syncToQuerySide(articleEntity);
    }

    private void syncToQuerySide(ArticleEntity article) {
        this.kafkaTemplate.send(MESSAGE_QUEUE_TOPIC, article);
    }
}
