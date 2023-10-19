package com.lenhatthanh.blog.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthorDto {
    private String name;
    private String email;
}
