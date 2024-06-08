package com.lenhatthanh.blog.modules.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class PostEventDto {
    private String parentId;
    private String title;
    private String metaTitle;
    private String content;
    private String summary;
    private String thumbnail;
    private String slug;
    private String status;
    private LocalDateTime publishedAt;
    private String userId;
    private List<String> categoryIds;
    private List<String> tagIds;
}
