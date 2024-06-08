package com.lenhatthanh.blog.modules.user.application.usecase;

import com.lenhatthanh.blog.modules.user.dto.LoginDto;
import com.lenhatthanh.blog.modules.user.dto.LoginResponseDto;

public interface UserLoginUseCase {
    LoginResponseDto execute(LoginDto loginDto);
}
