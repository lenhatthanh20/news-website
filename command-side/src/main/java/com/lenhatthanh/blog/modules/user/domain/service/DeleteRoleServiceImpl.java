package com.lenhatthanh.blog.modules.user.domain.service;

import com.lenhatthanh.blog.modules.user.domain.Role;
import com.lenhatthanh.blog.modules.user.domain.SystemRole;
import com.lenhatthanh.blog.modules.user.domain.event.RoleDeletedEvent;
import com.lenhatthanh.blog.modules.user.domain.exception.RoleNotFoundException;
import com.lenhatthanh.blog.modules.user.domain.exception.SystemRoleCannotBeModifiedException;
import com.lenhatthanh.blog.modules.user.domain.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteRoleServiceImpl implements DeleteRoleService {
    private final RoleRepository roleRepository;

    @Override
    public void delete(String roleId) {
        Role role = this.getRoleByIdOrError(roleId);
        this.isNotSystemRoleOrError(role.getName().getValue());

        role.registerEvent(new RoleDeletedEvent(role));
        roleRepository.delete(role);
    }

    private Role getRoleByIdOrError(String roleId) {
        return roleRepository.findById(roleId).orElseThrow(RoleNotFoundException::new);
    }

    private void isNotSystemRoleOrError(String roleName) {
        if(roleName.equals(SystemRole.ADMIN) || roleName.equals(SystemRole.AUTHOR) || roleName.equals(SystemRole.SUBSCRIBER)) {
            throw new SystemRoleCannotBeModifiedException();
        }
    }
}
