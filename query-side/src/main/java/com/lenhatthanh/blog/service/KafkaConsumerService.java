package com.lenhatthanh.blog.service;

import com.lenhatthanh.blog.model.Author;
import com.lenhatthanh.blog.repository.AuthorRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaConsumerService {
    RedisTemplate<String, String> redisTemplate;
    AuthorRepositoryInterface authorRepository;

    @KafkaListener(topics = "authors")
    public void listen(AuthorDto authorDto) {
        Author author = Author.of(authorDto.getId(), authorDto.getName(), authorDto.getEmail());
        authorRepository.save(author);
    }
}
