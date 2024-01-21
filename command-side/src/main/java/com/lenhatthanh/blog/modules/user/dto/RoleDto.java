package com.lenhatthanh.blog.modules.user.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoleDto {
    private String name;
    private String description;
}
