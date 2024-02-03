package com.lenhatthanh.blog.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserEventDto {
    private String id;
    private String name;
    private String email;
//    private String List<ArticleDto> articles;
    private String roleId;
}
