package com.lenhatthanh.blog.modules.user.application.usecase;

import com.lenhatthanh.blog.modules.user.domain.service.CreateUserService;
import com.lenhatthanh.blog.modules.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAuthorUserUseCase {
    CreateUserService createUserService;

    public void execute(UserDto userDto) {
        createUserService.createAuthor(userDto);
    }
}
