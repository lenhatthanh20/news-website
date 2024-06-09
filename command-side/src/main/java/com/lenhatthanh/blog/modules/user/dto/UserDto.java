package com.lenhatthanh.blog.modules.user.dto;

import lombok.*;

import java.util.List;

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

    private List<String> roleIds;

    public UserDto(String name, String email, String mobilePhone, String password) {
        this.name = name;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.password = password;
    }
}
