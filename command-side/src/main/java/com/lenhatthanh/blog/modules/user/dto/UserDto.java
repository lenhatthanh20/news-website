package com.lenhatthanh.blog.modules.user.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private String id;
    private String name;
    private String email;
    private String password;
    private String mobilePhone;

    public UserDto(String name, String email, String password, String mobilePhone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobilePhone = mobilePhone;
    }
}
