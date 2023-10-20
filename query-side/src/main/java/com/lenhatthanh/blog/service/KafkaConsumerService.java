package com.lenhatthanh.blog.service;

import com.lenhatthanh.blog.dto.ArticleDto;
import com.lenhatthanh.blog.dto.UserDto;
import com.lenhatthanh.blog.model.Article;
import com.lenhatthanh.blog.model.User;
import com.lenhatthanh.blog.model.Command;
import com.lenhatthanh.blog.repository.ArticleRepository;
import com.lenhatthanh.blog.repository.UserRepository;
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
    UserRepository userRepository;
    ArticleRepository articleRepository;
    ObjectConverter objectConverter;

    @KafkaListener(topics = "user")
    public void listenEventFromUserTopic(ConsumerRecord<String, byte[]> record) throws IOException {
        UserDto userDto = objectConverter.convertArrayByteToObject(record.value(), UserDto.class);
        String command = record.key();

        switch (command) {
            case Command.CREATED, Command.UPDATED -> userRepository.save(User.of(userDto.getId(), userDto.getName(), userDto.getEmail()));
            case Command.DELETED -> userRepository.deleteById(userDto.getId());
        }
    }

    @KafkaListener(topics = "article")
    public void listenEventFromArticleTopic(ConsumerRecord<String, byte[]> record) throws IOException {
        ArticleDto articleDto = objectConverter.convertArrayByteToObject(record.value(), ArticleDto.class);
        String command = record.key();

        switch (command) {
            case Command.CREATED, Command.UPDATED -> articleRepository.save(Article.of(
                    articleDto.getId(),
                    articleDto.getTitle(),
                    articleDto.getContent(),
                    articleDto.getUser(),
                    articleDto.getSummary(),
                    articleDto.getThumbnail(),
                    articleDto.getSlug(),
                    articleDto.getPublishedAt(),
                    articleDto.getCreatedAt(),
                    articleDto.getUpdatedAt()
            ));
            case Command.DELETED -> userRepository.deleteById(articleDto.getId());
        }
    }
}
