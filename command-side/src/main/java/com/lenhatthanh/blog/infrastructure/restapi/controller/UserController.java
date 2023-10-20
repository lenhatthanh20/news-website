package com.lenhatthanh.blog.infrastructure.restapi.controller;

import com.lenhatthanh.blog.application.usecase.CreateUser;
import com.lenhatthanh.blog.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private CreateUser createUserUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto user) {
        createUserUseCase.execute(user);
    }
}
