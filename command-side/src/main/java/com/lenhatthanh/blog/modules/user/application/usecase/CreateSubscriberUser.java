package com.lenhatthanh.blog.modules.user.application.usecase;

import com.lenhatthanh.blog.modules.user.domain.service.CreateUserServiceInterface;
import com.lenhatthanh.blog.modules.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateSubscriberUser {
    PasswordEncoder passwordEncoder;
    CreateUserServiceInterface createUserService;

    public void execute(UserDto userDto) {
        createUserService.createSubscriber(userDto);
    }
}
