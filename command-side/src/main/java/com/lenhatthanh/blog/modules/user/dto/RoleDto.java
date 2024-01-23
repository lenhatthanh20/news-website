package com.lenhatthanh.blog.modules.user.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoleDto {
    private String id;
    private String name;
    private String description;

    public RoleDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public RoleDto(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
