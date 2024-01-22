package com.lenhatthanh.blog.modules.user.application.usecase;

import com.lenhatthanh.blog.modules.user.application.service.AuthService;
import com.lenhatthanh.blog.modules.user.dto.LoginDto;
import com.lenhatthanh.blog.modules.user.dto.LoginResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserLogin {
    private AuthService authService;

    public LoginResponseDto execute(LoginDto loginDto) {
        return authService.login(loginDto);
    }
}
