package com.lenhatthanh.blog.modules.post.infra.persistence;

import com.lenhatthanh.blog.modules.post.application.repository.PostUserRepository;
import com.lenhatthanh.blog.modules.post.domain.entity.PostUser;
import com.lenhatthanh.blog.modules.user.domain.entity.User;
import com.lenhatthanh.blog.modules.user.infra.persistence.UserRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class PostUserRepositoryImpl implements PostUserRepository {
    UserRepositoryImpl userRepository;

    @Override
    public Optional<PostUser> findById(String id) {
        // In the microservice architecture,
        // We have `User` bounded context and `Post` bounded context.
        // That means we have two microservices for each bounded context.
        // So we can use Rest API (can be non-blocking) to get user information from `User` bounded context.
        Optional<User> userEntity = userRepository.findById(id);
        return userEntity.map(user -> PostUser.create(
                user.getId(),
                user.getName().getValue(),
                user.getEmail().getValue(),
                user.getMobilePhone().getValue(),
                user.getIsActive(),
                user.getRoleIds()
        ));
    }
}
