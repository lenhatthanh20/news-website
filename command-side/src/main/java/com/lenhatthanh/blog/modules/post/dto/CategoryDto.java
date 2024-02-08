package com.lenhatthanh.blog.modules.post.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDto {
    private String parentId = null;
    private String title;

    public CategoryDto(String title) {
        this.title = title;
    }
}
