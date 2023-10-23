package com.lenhatthanh.blog.infrastructure.restapi.controller;

import com.lenhatthanh.blog.application.usecase.CreateUser;
import com.lenhatthanh.blog.application.usecase.UserLogin;
import com.lenhatthanh.blog.dto.LoginDto;
import com.lenhatthanh.blog.dto.LoginResponseDto;
import com.lenhatthanh.blog.dto.UserDto;
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
