package com.lenhatthanh.blog.modules.user.application.usecase;

import com.lenhatthanh.blog.modules.user.domain.service.CreateRoleServiceInterface;
import com.lenhatthanh.blog.modules.user.dto.RoleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateRoleUseCase {
    CreateRoleServiceInterface createRoleService;

    public void execute(RoleDto roleDto) {
        createRoleService.create(roleDto);
    }
}
