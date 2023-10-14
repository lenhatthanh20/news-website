package com.lenhatthanh.blog.infrastructure.restapi.controller;

import com.lenhatthanh.blog.application.usecase.CreateAuthor;
import com.lenhatthanh.blog.infrastructure.restapi.requestmodel.CreateAuthorRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private CreateAuthor createAuthorUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAuthor(@RequestBody CreateAuthorRequest authorRequest) {
        createAuthorUseCase.execute(authorRequest);
    }
}
