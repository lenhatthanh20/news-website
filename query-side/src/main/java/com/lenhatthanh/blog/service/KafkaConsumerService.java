package com.lenhatthanh.blog.service;

import com.lenhatthanh.blog.model.Author;
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
    ObjectConverter objectConverter;

    @KafkaListener(topics = "authors")
    public void listen(ConsumerRecord<String, byte[]> record) throws IOException {
        AuthorDto authorDto = objectConverter.convertArrayByteToObject(record.value(), AuthorDto.class);
        String command = record.key();

        switch (command) {
            case Command.CREATED, Command.UPDATED -> authorRepository.save(Author.of(authorDto.getId(), authorDto.getName(), authorDto.getEmail()));
            case Command.DELETED -> authorRepository.deleteById(authorDto.getId());
        }
    }
}
