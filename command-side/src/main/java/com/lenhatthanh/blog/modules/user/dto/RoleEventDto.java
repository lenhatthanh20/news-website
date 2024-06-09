package com.lenhatthanh.blog.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoleEventDto {
    private String id;
    private String name;
    private String description;
    private Boolean isDeleted;
}
