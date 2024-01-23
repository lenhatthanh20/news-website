package com.lenhatthanh.blog.modules.user.application.usecase;

import com.lenhatthanh.blog.modules.user.domain.service.UpdateRoleServiceInterface;
import com.lenhatthanh.blog.modules.user.dto.RoleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateRoleUseCase {
    UpdateRoleServiceInterface updateRoleService;

    public void execute(RoleDto roleDto) {
        updateRoleService.update(roleDto);
    }
}
