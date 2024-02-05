package com.lenhatthanh.blog.modules.user.infrastructure.repository;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.core.domain.DomainEventPublisher;
import com.lenhatthanh.blog.modules.user.domain.Email;
import com.lenhatthanh.blog.modules.user.domain.MobilePhone;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.UserName;
import com.lenhatthanh.blog.modules.user.domain.repository.UserRepository;
import com.lenhatthanh.blog.modules.user.infrastructure.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private UserJpaRepository userJpaRepository;
    private DomainEventPublisher domainEventPublisher;

    @Override
    public void save(User user) {
        UserEntity userEntity = new UserEntity(
                user.getId().toString(),
                user.getAggregateVersion(),
                user.getName().getValue(),
                user.getEmail().getValue(),
                user.getMobilePhone().getValue(),
                user.getPassword(),
                user.getIsActive()
        );

        user.getRoleIds().forEach(roleId -> {
            userEntity.addRole(roleId.toString());
        });

        this.userJpaRepository.save(userEntity);
        // Publish domain events
        user.publishEvents(domainEventPublisher);
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<UserEntity> userEntity = this.userJpaRepository.findById(id);
        if (userEntity.isEmpty()) {
            return Optional.empty();
        }

        User user = new User(
                new Id(userEntity.get().getId()),
                userEntity.get().getVersion(),
                new UserName(userEntity.get().getName()),
                new Email(userEntity.get().getEmail()),
                new MobilePhone(userEntity.get().getMobilePhone()),
                userEntity.get().getPassword(),
                userEntity.get().getIsActive()
        );

        return Optional.of(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> userEntity = this.userJpaRepository.findByEmail(email);
        if (userEntity.isEmpty()) {
            return Optional.empty();
        }

        User user = new User(
                new Id(userEntity.get().getId()),
                userEntity.get().getVersion(),
                new UserName(userEntity.get().getName()),
                new Email(userEntity.get().getEmail()),
                new MobilePhone(userEntity.get().getMobilePhone()),
                userEntity.get().getPassword(),
                userEntity.get().getIsActive()
        );

        return Optional.of(user);
    }
}
