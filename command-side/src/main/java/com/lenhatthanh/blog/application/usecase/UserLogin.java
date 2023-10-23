package com.lenhatthanh.blog.application.usecase;

import com.lenhatthanh.blog.application.service.AuthService;
import com.lenhatthanh.blog.dto.LoginDto;
import com.lenhatthanh.blog.dto.LoginResponseDto;
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
