package com.lenhatthanh.blog.infrastructure.restapi.requestmodel;

import lombok.*;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateArticleRequest {
    private String title;
    private String content;
    private String authorId;
    private String slug;
    private String summary;
    private String thumbnail;
}
