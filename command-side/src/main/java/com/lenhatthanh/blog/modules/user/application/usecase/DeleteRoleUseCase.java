package com.lenhatthanh.blog.modules.user.application.usecase;

import com.lenhatthanh.blog.modules.user.domain.service.DeleteRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteRoleUseCase {
    DeleteRoleService deleteRoleService;

    public void execute(String roleId) {
        deleteRoleService.delete(roleId);
    }
}
