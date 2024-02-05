package com.lenhatthanh.blog.modules.user.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String id;
    private String name;
    private String email;
    private String mobilePhone;
    private String password;

    public UserDto(String name, String email, String mobilePhone, String password) {
        this.name = name;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.password = password;
    }
}
