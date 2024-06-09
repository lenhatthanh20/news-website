package com.lenhatthanh.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String name;
    private String email;
    private String password;
    private List<RoleDto> roles;

    public UserDto(String id, String name, String email, List<RoleDto> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public UserDto(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void addRole(RoleDto role) {
        roles.add(role);
    }
}
