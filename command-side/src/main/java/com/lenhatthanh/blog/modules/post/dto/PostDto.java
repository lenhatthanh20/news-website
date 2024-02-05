package com.lenhatthanh.blog.modules.post.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostDto {
    private String title;
    private String content;
    private String userId;
    private String slug;
    private String summary;
    private String thumbnail;
}
