package com.lenhatthanh.blog.modules.user.application.externalservice;

import com.lenhatthanh.blog.modules.user.dto.LoginDto;
import com.lenhatthanh.blog.modules.user.dto.LoginResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginDto loginDto);
}
