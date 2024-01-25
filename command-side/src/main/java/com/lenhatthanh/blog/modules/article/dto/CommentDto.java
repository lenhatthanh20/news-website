package com.lenhatthanh.blog.modules.article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentDto {
    private String content;
    private String userId;
}
