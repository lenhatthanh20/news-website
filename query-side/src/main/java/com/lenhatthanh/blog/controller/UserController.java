package com.lenhatthanh.blog.controller;

import com.lenhatthanh.blog.dto.UserForViewDto;
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
    public ResponseEntity<List<UserForViewDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("email/{email}")
    public ResponseEntity<UserForViewDto> findByEmail(@PathVariable("email") String email) {
        // Validate dữ liệu ==> chứa một số logics
        return ResponseEntity.ok(userService.findByEmail(email));
    }
}
