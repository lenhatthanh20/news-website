package com.lenhatthanh.blog.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private String id;
    private String title;
    private String content;
    private UserDto user;
    private String summary;
    private String thumbnail;
    private SlugDto slug;
    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
