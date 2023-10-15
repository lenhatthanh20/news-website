package com.lenhatthanh.blog.service;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaConsumerService {
    RedisTemplate<String, String> redisTemplate;
    @KafkaListener(topics = "authors")
    public void listen(String message) {

        // Parse the message

        // Save the data to Redis
        redisTemplate.opsForHash().put("authors", "authors-1", message);
    }
}
