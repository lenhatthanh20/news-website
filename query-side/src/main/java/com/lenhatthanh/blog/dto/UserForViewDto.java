package com.lenhatthanh.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForViewDto {
    private String id;
    private String name;
    private String email;
    private List<RoleDto> roles = new ArrayList<>();

    public UserForViewDto(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void addRole(RoleDto role) {
        roles.add(role);
    }
}
