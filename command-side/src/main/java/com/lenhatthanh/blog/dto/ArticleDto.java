package com.lenhatthanh.blog.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ArticleDto {
    private String title;
    private String content;
    private String userId;
    private String slug;
    private String summary;
    private String thumbnail;
}
