package com.lenhatthanh.blog.modules.post.infra.persistence;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.post.application.repository.PostUserRepository;
import com.lenhatthanh.blog.modules.post.domain.entity.PostUser;
import com.lenhatthanh.blog.modules.user.infra.persistence.UserJpaRepository;
import com.lenhatthanh.blog.modules.user.infra.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class PostUserRepositoryImpl implements PostUserRepository {
    UserJpaRepository userRepository;

    @Override
    public Optional<PostUser> findById(String id) {
        // In the microservice architecture,
        // We have `User` bounded context and `Post` bounded context.
        // That means we have two microservices for each bounded context.
        // So we can use Rest API (can be non-blocking) to get user information from `User` bounded context.
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.map(user -> PostUser.create(
                new Id(user.getId()),
                user.getName(),
                user.getEmail(),
                user.getMobilePhone(),
                user.getIsActive(),
                user.getIsDeleted(),
                user.getRoleIds().stream().map(Id::new).toList()
        ));
    }
}
