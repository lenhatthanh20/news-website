package com.lenhatthanh.blog.modules.user.infra.persistence;

import com.lenhatthanh.blog.modules.user.domain.entity.User;
import com.lenhatthanh.blog.modules.user.application.repository.UserRepository;
import com.lenhatthanh.blog.modules.user.infra.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private UserJpaRepository userJpaRepository;

    @Override
    public void save(User user) {
        UserEntity userEntity = UserEntity.fromDomainModel(user);
        userJpaRepository.save(userEntity);
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<UserEntity> userEntity = userJpaRepository.findById(id);

        return userEntity.map(UserEntity::toDomainModel);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> userEntity = userJpaRepository.findByEmail(email);

        return userEntity.map(UserEntity::toDomainModel);
    }

    @Override
    public void delete(User user) {
        userJpaRepository.deleteById(user.getId().toString());
    }
}
