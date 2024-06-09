package com.lenhatthanh.blog.modules.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class LoggedInUserDto {
    private String id;
    private String email;
    private List<String> roleIds;
}
