package com.lenhatthanh.blog.modules.user.domain.service;

import com.lenhatthanh.blog.modules.user.dto.RoleDto;

import java.util.List;

public interface CreateRoleService {
    void create(RoleDto roleDto);
    void createList(List<RoleDto> roleDtoList);
}
