package com.lenhatthanh.blog.modules.user.infrastructure.rest_api;

import com.lenhatthanh.blog.modules.user.application.usecase.CreateUser;
import com.lenhatthanh.blog.modules.user.application.usecase.UserLogin;
import com.lenhatthanh.blog.modules.user.dto.LoginDto;
import com.lenhatthanh.blog.modules.user.dto.LoginResponseDto;
import com.lenhatthanh.blog.modules.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private CreateUser createUserUseCase;
    private UserLogin userLoginUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto user) {
        createUserUseCase.execute(user);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginUser) {
        return ResponseEntity.ok(userLoginUseCase.execute(loginUser));
    }
}
