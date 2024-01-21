package com.lenhatthanh.blog.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class LoginResponseDto {
    private String UserId;
    private String token;
}
