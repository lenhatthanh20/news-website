package com.lenhatthanh.blog.modules.post.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    private List<String> categoryIds = new ArrayList<>();
    private List<String> tagIds = new ArrayList<>();
}
