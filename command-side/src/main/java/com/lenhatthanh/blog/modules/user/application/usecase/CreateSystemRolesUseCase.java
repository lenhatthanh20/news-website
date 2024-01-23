package com.lenhatthanh.blog.modules.user.application.usecase;

import com.lenhatthanh.blog.modules.user.domain.service.CreateRoleServiceInterface;
import com.lenhatthanh.blog.modules.user.dto.RoleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CreateSystemRolesUseCase {
    CreateRoleServiceInterface createRoleService;

    public void execute(List<RoleDto> roleDtoList) {
        createRoleService.createList(roleDtoList);
    }
}
