package com.lenhatthanh.blog.modules.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentDto {
    private String parentId;
    private String content;
    private String userId;
    private String postId;
}
