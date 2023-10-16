package com.lenhatthanh.blog.infrastructure.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private String id;
    private String title;
    private String content;
    private AuthorDto author;
}
