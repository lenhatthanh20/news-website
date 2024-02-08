package com.lenhatthanh.blog.modules.post.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostDto {
    private String parentId;
    private String title;
    private String metaTitle;
    private String content;
    private String userId;
    private String slug;
    private String summary;
    private String thumbnail;

    private Set<String> categoryIds = new HashSet<>();
    private Set<String> tagIds = new HashSet<>();
}
