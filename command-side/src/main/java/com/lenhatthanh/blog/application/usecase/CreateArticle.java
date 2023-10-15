package com.lenhatthanh.blog.application.usecase;

import com.lenhatthanh.blog.application.exception.AuthorNotFoundException;
import com.lenhatthanh.blog.domain.article.Article;
import com.lenhatthanh.blog.domain.article.Author;
import com.lenhatthanh.blog.domain.repository.ArticleRepositoryInterface;
import com.lenhatthanh.blog.domain.repository.AuthorRepositoryInterface;
import com.lenhatthanh.blog.infrastructure.restapi.requestmodel.CreateArticleRequest;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CreateArticle {
    public final String TOPIC = "articles";
    public final String EVENT = "created";

    private ArticleRepositoryInterface articleRepository;
    private AuthorRepositoryInterface authorRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void execute(CreateArticleRequest articleRequest) {
        Optional<Author> author = authorRepository.findById(articleRequest.getAuthorId());
        if (author.isEmpty()) {
            throw new AuthorNotFoundException("APPLICATION-ERROR-0001");
        }

        Article article = new Article(
                UUID.randomUUID().toString(),
                articleRequest.getTitle(),
                articleRequest.getContent(),
                author.get()
        );

        articleRepository.save(article);
        this.syncToQuerySide(article);
    }

    private void syncToQuerySide(Article article) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(this.TOPIC, this.EVENT, article.toString());
        kafkaTemplate.send(producerRecord);
    }
}
