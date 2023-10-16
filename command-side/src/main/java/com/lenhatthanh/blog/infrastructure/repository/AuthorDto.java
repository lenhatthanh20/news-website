package com.lenhatthanh.blog.infrastructure.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorDto {
    private String id;
    private String name;
    private String email;
    private AuthorCommand command;
}
