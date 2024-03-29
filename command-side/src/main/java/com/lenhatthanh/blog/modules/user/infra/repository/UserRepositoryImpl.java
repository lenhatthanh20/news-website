package com.lenhatthanh.blog.modules.user.infra.repository;

import com.lenhatthanh.blog.core.domain.DomainEventPublisher;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.repository.UserRepository;
import com.lenhatthanh.blog.modules.user.infra.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private UserJpaRepository userJpaRepository;
    private DomainEventPublisher domainEventPublisher;

    @Override
    @Transactional
    public void save(User user) {
        UserEntity userEntity = UserEntity.fromDomainModel(user);
        this.userJpaRepository.save(userEntity);
        user.publishEvents(domainEventPublisher);
    }

    @Override
    @Transactional
    public Optional<User> findById(String id) {
        Optional<UserEntity> userEntity = this.userJpaRepository.findById(id);

        return userEntity.map(UserEntity::toDomainModel);
    }

    @Override
    @Transactional
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> userEntity = this.userJpaRepository.findByEmail(email);

        return userEntity.map(UserEntity::toDomainModel);
    }

    @Override
    @Transactional
    public void delete(User user) {
        this.userJpaRepository.deleteById(user.getId().toString());
        user.publishEvents(domainEventPublisher);
    }
}
