package com.lenhatthanh.blog.controller;

import com.lenhatthanh.blog.service.AuthorDto;
import com.lenhatthanh.blog.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAll() {
        return ResponseEntity.ok(authorService.getAll());
    }

    @GetMapping("email/{email}")
    public ResponseEntity<AuthorDto> findByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(authorService.findByEmail(email));
    }
}
