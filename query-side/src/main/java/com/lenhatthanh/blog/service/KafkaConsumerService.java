package com.lenhatthanh.blog.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lenhatthanh.blog.model.Author;
import com.lenhatthanh.blog.repository.AuthorRepositoryInterface;
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
    AuthorRepositoryInterface authorRepository;

    @KafkaListener(topics = "authors")
    public void listen(ConsumerRecord<String, byte[]> record) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        AuthorDto authordto = objectMapper.reader().readValue(record.value(), AuthorDto.class);

        authorRepository.save(Author.of(authordto.getId(), authordto.getName(), authordto.getEmail()));
    }
}
