package com.lenhatthanh.blog.service;

import com.lenhatthanh.blog.dto.ArticleDto;
import com.lenhatthanh.blog.dto.AuthorDto;
import com.lenhatthanh.blog.model.Article;
import com.lenhatthanh.blog.model.Author;
import com.lenhatthanh.blog.model.Command;
import com.lenhatthanh.blog.repository.ArticleRepository;
import com.lenhatthanh.blog.repository.AuthorRepository;
import com.lenhatthanh.blog.shared.ObjectConverter;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class KafkaConsumerService {
    RedisTemplate<String, String> redisTemplate;
    AuthorRepository authorRepository;
    ArticleRepository articleRepository;
    ObjectConverter objectConverter;

    @KafkaListener(topics = "authors")
    public void listenEventFromAuthorsTopic(ConsumerRecord<String, byte[]> record) throws IOException {
        AuthorDto authorDto = objectConverter.convertArrayByteToObject(record.value(), AuthorDto.class);
        String command = record.key();

        switch (command) {
            case Command.CREATED, Command.UPDATED -> authorRepository.save(Author.of(authorDto.getId(), authorDto.getName(), authorDto.getEmail()));
            case Command.DELETED -> authorRepository.deleteById(authorDto.getId());
        }
    }

    @KafkaListener(topics = "articles")
    public void listenEventFromArticlesTopic(ConsumerRecord<String, byte[]> record) throws IOException {
        ArticleDto articleDto = objectConverter.convertArrayByteToObject(record.value(), ArticleDto.class);
        String command = record.key();

        switch (command) {
            case Command.CREATED, Command.UPDATED -> articleRepository.save(Article.of(
                    articleDto.getId(),
                    articleDto.getTitle(),
                    articleDto.getContent(),
                    articleDto.getAuthor(),
                    articleDto.getSummary(),
                    articleDto.getThumbnail(),
                    articleDto.getSlug(),
                    articleDto.getPublishedAt(),
                    articleDto.getCreatedAt(),
                    articleDto.getUpdatedAt()
            ));
            case Command.DELETED -> authorRepository.deleteById(articleDto.getId());
        }
    }
}
