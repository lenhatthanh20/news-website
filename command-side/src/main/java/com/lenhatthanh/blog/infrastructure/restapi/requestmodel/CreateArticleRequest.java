package com.lenhatthanh.blog.infrastructure.restapi.requestmodel;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateArticleRequest {
    private String title;
    private String content;
    private String authorId;
}
