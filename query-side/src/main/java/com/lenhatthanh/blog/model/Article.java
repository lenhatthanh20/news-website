package com.lenhatthanh.blog.model;

import com.lenhatthanh.blog.dto.UserDto;
import com.lenhatthanh.blog.dto.SlugDto;
import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Article {
    @Indexed
    @Id
    private String id;

    @NonNull
    @Indexed
    private String articleId;

    @NonNull
    @Indexed
    private String title;

    @NonNull
    @Searchable
    private String content;

    @NonNull
    @Indexed
    private UserDto user;

    @NonNull
    @Searchable
    private String summary;

    @NonNull
    private String thumbnail;

    @NonNull
    @Indexed
    private SlugDto slug;

    @NonNull
    private LocalDateTime publishedAt;

    @NonNull
    private LocalDateTime createdAt;

    @NonNull
    private LocalDateTime updatedAt;

    public Article(
            @NonNull String id,
            @NonNull String title,
            @NonNull String content,
            @NonNull UserDto user,
            @NonNull String summary,
            @NonNull String thumbnail,
            @NonNull SlugDto slug,
            @NonNull LocalDateTime publishedAt,
            @NonNull LocalDateTime createdAt,
            @NonNull LocalDateTime updatedAt
    ) {
        this.articleId = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.summary = summary;
        this.thumbnail = thumbnail;
        this.slug = slug;
        this.publishedAt = publishedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
