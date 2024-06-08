package com.lenhatthanh.blog.modules.user.application.usecase.implement;

import com.lenhatthanh.blog.modules.user.application.usecase.UpdateRoleUseCase;
import com.lenhatthanh.blog.modules.user.domain.Role;
import com.lenhatthanh.blog.modules.user.domain.RoleDescription;
import com.lenhatthanh.blog.modules.user.domain.RoleName;
import com.lenhatthanh.blog.modules.user.domain.SystemRole;
import com.lenhatthanh.blog.modules.user.domain.exception.RoleAlreadyExistException;
import com.lenhatthanh.blog.modules.user.domain.exception.RoleNotFoundException;
import com.lenhatthanh.blog.modules.user.domain.exception.SystemRoleCannotBeModifiedException;
import com.lenhatthanh.blog.modules.user.application.repository.RoleRepository;
import com.lenhatthanh.blog.modules.user.dto.RoleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateRoleUseCaseImpl implements UpdateRoleUseCase {
    RoleRepository roleRepository;

    public void execute(RoleDto newRoleDto) {
        Role currentRole = this.roleMustExistByIdOrError(newRoleDto.getId());
        if (!currentRole.getName().getValue().equals(newRoleDto.getName())) {
            this.newRoleNameDoesNotExistOrError(newRoleDto.getName());
        }

        this.isNotSystemRoleOrError(currentRole.getName().getValue());

        currentRole.updateRoleName(new RoleName(newRoleDto.getName()));
        currentRole.updateDescription(new RoleDescription(newRoleDto.getDescription()));

        roleRepository.save(currentRole);
    }

    private void isNotSystemRoleOrError(String roleName) {
        if (roleName.equals(SystemRole.ADMIN) || roleName.equals(SystemRole.AUTHOR) || roleName.equals(SystemRole.SUBSCRIBER)) {
            throw new SystemRoleCannotBeModifiedException();
        }
    }

    private Role roleMustExistByIdOrError(String id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isEmpty()) {
            throw new RoleNotFoundException();
        }

        return role.get();
    }

    private void newRoleNameDoesNotExistOrError(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isPresent()) {
            throw new RoleAlreadyExistException();
        }
    }
}
