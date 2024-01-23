package com.lenhatthanh.blog.modules.user.infrastructure.repository;

import com.lenhatthanh.blog.core.domain.AggregateId;
import com.lenhatthanh.blog.core.domain.DomainEventPublisher;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.repository.UserRepositoryInterface;
import com.lenhatthanh.blog.modules.user.infrastructure.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserRepository implements UserRepositoryInterface {
    private UserJpaRepository userJpaRepository;
    private DomainEventPublisher domainEventPublisher;

    @Override
    public void save(User user) {
        UserEntity userEntity = new UserEntity(
                user.getId().toString(),
                user.getName().getValue(),
                user.getEmail().getValue(),
                user.getPassword()
        );

        user.getRoleIds().forEach(roleId -> {
            userEntity.addRole(roleId.toString());
        });

        // Publish domain events
        user.publishEvents(domainEventPublisher);

        this.userJpaRepository.save(userEntity);
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<UserEntity> userEntity = this.userJpaRepository.findById(id);
        if (userEntity.isEmpty()) {
            return Optional.empty();
        }

        User user = User.create(
                new AggregateId(userEntity.get().getId()),
                userEntity.get().getName(),
                userEntity.get().getEmail(),
                userEntity.get().getPassword()
        );

        return Optional.of(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> userEntity = this.userJpaRepository.findByEmail(email);
        if (userEntity.isEmpty()) {
            return Optional.empty();
        }

        User user = User.create(
                new AggregateId(userEntity.get().getId()),
                userEntity.get().getName(),
                userEntity.get().getEmail(),
                userEntity.get().getPassword()
        );

        return Optional.of(user);
    }
}
