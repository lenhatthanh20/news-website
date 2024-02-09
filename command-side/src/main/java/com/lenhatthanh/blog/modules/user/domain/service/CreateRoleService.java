package com.lenhatthanh.blog.modules.user.domain.service;

import com.lenhatthanh.blog.modules.user.dto.RoleDto;

import java.util.Set;

public interface CreateRoleService {
    void create(RoleDto roleDto);
    void createList(Set<RoleDto> roleDtoList);
}
