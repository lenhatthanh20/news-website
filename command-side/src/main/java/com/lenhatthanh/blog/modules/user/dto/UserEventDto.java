package com.lenhatthanh.blog.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class UserEventDto {
    private String id;
    private String name;
    private String email;
    private String mobilePhone;
    private Boolean isActive;
    private Set<String> roleIds;
}
