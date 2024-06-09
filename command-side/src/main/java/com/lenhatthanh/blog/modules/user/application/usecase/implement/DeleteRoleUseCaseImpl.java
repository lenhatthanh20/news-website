package com.lenhatthanh.blog.modules.user.application.usecase.implement;

import com.lenhatthanh.blog.modules.user.application.evenpublisher.RoleEventPublisher;
import com.lenhatthanh.blog.modules.user.application.usecase.DeleteRoleUseCase;
import com.lenhatthanh.blog.modules.user.domain.Role;
import com.lenhatthanh.blog.modules.user.domain.SystemRole;
import com.lenhatthanh.blog.modules.user.domain.exception.RoleNotFoundException;
import com.lenhatthanh.blog.modules.user.domain.exception.SystemRoleCannotBeModifiedException;
import com.lenhatthanh.blog.modules.user.application.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class DeleteRoleUseCaseImpl implements DeleteRoleUseCase {
    private final RoleRepository roleRepository;
    RoleEventPublisher publisher;

    public void execute(String roleId) {
        Role role = getRoleByIdOrError(roleId);
        isNotSystemRoleOrError(role.getName().getValue());
        role.delete();
        roleRepository.delete(role);
        publishDomainEvents(role);
    }

    private Role getRoleByIdOrError(String roleId) {
        return roleRepository.findById(roleId).orElseThrow(RoleNotFoundException::new);
    }

    private void isNotSystemRoleOrError(String roleName) {
        if (roleName.equals(SystemRole.ADMIN) || roleName.equals(SystemRole.AUTHOR) || roleName.equals(SystemRole.SUBSCRIBER)) {
            throw new SystemRoleCannotBeModifiedException();
        }
    }

    private void publishDomainEvents(Role role) {
        role.getDomainEvents().forEach(event -> publisher.publish(event));
    }
}
