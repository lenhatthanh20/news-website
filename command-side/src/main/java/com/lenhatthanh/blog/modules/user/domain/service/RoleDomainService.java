package com.lenhatthanh.blog.modules.user.domain.service;

import com.lenhatthanh.blog.modules.user.domain.entity.Role;
import com.lenhatthanh.blog.modules.user.dto.RoleDto;

public interface RoleDomainService {
    Role createNewRole(RoleDto roleDto);
    void updateRole(String id, String name, String description);
    void deleteRole(String id);
}
