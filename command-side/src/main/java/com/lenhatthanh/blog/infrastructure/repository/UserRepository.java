package com.lenhatthanh.blog.infrastructure.repository;

import com.lenhatthanh.blog.domain.Role;
import com.lenhatthanh.blog.domain.User;
import com.lenhatthanh.blog.domain.Command;
import com.lenhatthanh.blog.domain.repository.UserRepositoryInterface;
import com.lenhatthanh.blog.infrastructure.repository.entity.RoleEntity;
import com.lenhatthanh.blog.infrastructure.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserRepository implements UserRepositoryInterface {
    public static final String MESSAGE_QUEUE_TOPIC = "user";

    private UserJpaRepository userJpaRepository;
    private KafkaTemplate<String, UserEntity> kafkaTemplate;

    @Override
    public void save(User user) {
        UserEntity userEntity = UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(new ArrayList<>())
                .build();

        for (Role role : user.getRoles()) {
            RoleEntity roleEntity = new RoleEntity(role.getId(), role.getName(), role.getDescription());
            userEntity.addRole(roleEntity);
        }

        this.userJpaRepository.save(userEntity);
        this.syncToQuerySide(userEntity);
    }

    private void syncToQuerySide(UserEntity user) {
        ProducerRecord<String, UserEntity> record = new ProducerRecord<>(MESSAGE_QUEUE_TOPIC, Command.CREATED, user);
        this.kafkaTemplate.send(record);
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<UserEntity> userEntity = this.userJpaRepository.findById(id);
        if (userEntity.isEmpty()) {
            return Optional.empty();
        }

        User user = new User(userEntity.get().getId(), userEntity.get().getName(), userEntity.get().getEmail(), userEntity.get().getPassword());

        return Optional.of(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> userEntity = this.userJpaRepository.findByEmail(email);
        if (userEntity.isEmpty()) {
            return Optional.empty();
        }

        User user = new User(userEntity.get().getId(), userEntity.get().getName(), userEntity.get().getEmail(), userEntity.get().getPassword());

        return Optional.of(user);
    }
}
