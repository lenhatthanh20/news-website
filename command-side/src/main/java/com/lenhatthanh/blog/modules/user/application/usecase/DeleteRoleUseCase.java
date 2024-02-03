package com.lenhatthanh.blog.modules.user.application.usecase;

import com.lenhatthanh.blog.modules.user.domain.service.DeleteRoleServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteRoleUseCase {
    DeleteRoleServiceInterface deleteRoleService;

    public void execute(String roleId) {
        deleteRoleService.delete(roleId);
    }
}
