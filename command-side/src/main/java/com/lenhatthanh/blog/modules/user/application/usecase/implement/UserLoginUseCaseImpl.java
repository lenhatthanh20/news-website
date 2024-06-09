package com.lenhatthanh.blog.modules.user.application.usecase.implement;

import com.lenhatthanh.blog.modules.user.application.externalservice.AuthService;
import com.lenhatthanh.blog.modules.user.application.usecase.UserLoginUseCase;
import com.lenhatthanh.blog.modules.user.dto.LoginDto;
import com.lenhatthanh.blog.modules.user.dto.LoginResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserLoginUseCaseImpl implements UserLoginUseCase {
    private AuthService authService;

    public LoginResponseDto execute(LoginDto loginDto) {
        return authService.login(loginDto);
    }
}
