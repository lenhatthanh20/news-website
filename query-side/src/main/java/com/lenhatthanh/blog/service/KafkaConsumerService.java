package com.lenhatthanh.blog.service;

import com.lenhatthanh.blog.dto.ArticleDto;
import com.lenhatthanh.blog.dto.RoleDto;
import com.lenhatthanh.blog.dto.UserDto;
import com.lenhatthanh.blog.model.Article;
import com.lenhatthanh.blog.model.Role;
import com.lenhatthanh.blog.model.User;
import com.lenhatthanh.blog.model.Command;
import com.lenhatthanh.blog.repository.ArticleRepository;
import com.lenhatthanh.blog.repository.RoleRepository;
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
    RoleRepository roleRepository;
    ObjectConverter objectConverter;

    @KafkaListener(topics = "user")
    public void listenEventFromUserTopic(ConsumerRecord<String, byte[]> record) throws IOException {
        UserDto userDto = objectConverter.convertArrayByteToObject(record.value(), UserDto.class);
        String command = record.key();
        switch (command) {
            case Command.CREATED, Command.UPDATED -> handleWriteUserEvent(userDto);
            case Command.DELETED -> userRepository.deleteById(userDto.getId());
        }
    }

    private void handleWriteUserEvent(UserDto userDto) {
        User user = new User(userDto.getId(), userDto.getName(), userDto.getEmail(), userDto.getPassword());
        userDto.getRoles().stream().map(roleDto -> new Role(roleDto.getId(), roleDto.getName(), roleDto.getDescription())).forEach(user::addRole);
        userRepository.save(user);
    }

    @KafkaListener(topics = "article")
    public void listenEventFromArticleTopic(ConsumerRecord<String, byte[]> record) throws IOException {
        ArticleDto articleDto = objectConverter.convertArrayByteToObject(record.value(), ArticleDto.class);
        String command = record.key();

        switch (command) {
            case Command.CREATED, Command.UPDATED -> articleRepository.save(new Article(
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
            case Command.DELETED -> articleRepository.deleteById(articleDto.getId());
        }
    }

    @KafkaListener(topics = "role")
    public void listenEventFromRoleTopic(ConsumerRecord<String, byte[]> record) throws IOException {
        RoleDto roleDto = objectConverter.convertArrayByteToObject(record.value(), RoleDto.class);
        String command = record.key();

        switch (command) {
            case Command.CREATED, Command.UPDATED -> roleRepository.save(new Role(roleDto.getId(), roleDto.getName(), roleDto.getDescription()));
            case Command.DELETED -> roleRepository.deleteById(roleDto.getId());
        }
    }
}
