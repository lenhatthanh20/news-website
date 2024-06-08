package com.lenhatthanh.blog.modules.user.application.usecase;

import com.lenhatthanh.blog.modules.user.dto.RoleDto;

public interface UpdateRoleUseCase {
    public void execute(RoleDto newRoleDto);
}
