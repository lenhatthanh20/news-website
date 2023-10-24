package com.lenhatthanh.blog.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class UserForViewDto {
    private String id;
    private String name;
    private String email;
    private List<RoleDto> roles;

    public UserForViewDto(String id, String name, String email, List<RoleDto> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public UserForViewDto(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void addRole(RoleDto role) {
        this.roles.add(role);
    }
}
