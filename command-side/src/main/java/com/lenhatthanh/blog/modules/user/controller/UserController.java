package com.lenhatthanh.blog.modules.user.controller;

import com.lenhatthanh.blog.modules.user.application.usecase.CreateUserUseCase;
import com.lenhatthanh.blog.modules.user.application.usecase.DeleteAuthorUserUseCase;
import com.lenhatthanh.blog.modules.user.application.usecase.UpdateAuthorUserUseCase;
import com.lenhatthanh.blog.modules.user.application.usecase.UserLoginUseCase;
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
    private CreateUserUseCase createUserUseCase;
    private UpdateAuthorUserUseCase updateAuthorUserUseCase;
    private DeleteAuthorUserUseCase deleteAuthorUserUseCase;
    private UserLoginUseCase userLoginUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto user) {
        createUserUseCase.execute(user);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody UserDto user) {
        updateAuthorUserUseCase.execute(user);
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String id) {
        deleteAuthorUserUseCase.execute(id);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginUser) {
        return ResponseEntity.ok(userLoginUseCase.execute(loginUser));
    }
}
