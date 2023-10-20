package com.lenhatthanh.blog.controller;

import com.lenhatthanh.blog.dto.UserDto;
import com.lenhatthanh.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("email/{email}")
    public ResponseEntity<UserDto> findByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }
}
