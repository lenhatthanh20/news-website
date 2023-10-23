package com.lenhatthanh.blog.application.usecase;

import com.lenhatthanh.blog.domain.User;
import com.lenhatthanh.blog.domain.repository.UserRepositoryInterface;
import com.lenhatthanh.blog.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CreateUser {
    private UserRepositoryInterface userRepository;

    public void execute(UserDto userDto) {
        User user = new User(
                UUID.randomUUID().toString(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getPassword()
        );

        userRepository.save(user);
    }
}
