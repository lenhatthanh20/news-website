package com.lenhatthanh.blog.modules.article.infrastructure.repository;

import com.lenhatthanh.blog.modules.article.domain.Article;
import com.lenhatthanh.blog.modules.article.domain.repository.ArticleRepositoryInterface;
import com.lenhatthanh.blog.modules.article.infrastructure.repository.entity.ArticleEntity;
import com.lenhatthanh.blog.modules.user.infrastructure.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class ArticleRepository implements ArticleRepositoryInterface {
//    public static final String MESSAGE_QUEUE_TOPIC = "article";
    private ArticleJpaRepository articleJpaRepository;
//    private KafkaTemplate<String, ArticleEntity> kafkaTemplate;

    @Override
    public void save(Article article) {
        UserEntity user = new UserEntity();
        user.setId(article.getUserId());

        LocalDateTime createAt = LocalDateTime.now();
        ArticleEntity articleEntity = ArticleEntity.builder()
                .id(article.getId().toString())
                .title(article.getTitle())
                .content(article.getContent())
                .user(user)
                .summary(article.getSummary())
                .thumbnail(article.getThumbnail())
                .slug(article.getSlug().getValue())
                .publishedAt(createAt)
                .createdAt(createAt)
                .updatedAt(createAt)
                .build();

        this.articleJpaRepository.save(articleEntity);
//        this.syncToQuerySide(articleEntity);
    }

//    private void syncToQuerySide(ArticleEntity article) {
//        ProducerRecord<String, ArticleEntity> record = new ProducerRecord<>(MESSAGE_QUEUE_TOPIC, Command.CREATED, article);
//        this.kafkaTemplate.send(record);
//    }
}
