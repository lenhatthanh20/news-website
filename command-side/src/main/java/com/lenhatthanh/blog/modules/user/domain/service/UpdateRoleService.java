package com.lenhatthanh.blog.modules.user.domain.service;

import com.lenhatthanh.blog.modules.user.domain.Role;
import com.lenhatthanh.blog.modules.user.domain.RoleDescription;
import com.lenhatthanh.blog.modules.user.domain.RoleName;
import com.lenhatthanh.blog.modules.user.domain.exception.RoleAlreadyExistException;
import com.lenhatthanh.blog.modules.user.domain.exception.RoleNotFoundException;
import com.lenhatthanh.blog.modules.user.domain.repository.RoleRepositoryInterface;
import com.lenhatthanh.blog.modules.user.dto.RoleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateRoleService implements UpdateRoleServiceInterface {
    RoleRepositoryInterface roleRepository;

    public void update(RoleDto newRoleDto) {
        Role currentRole = this.roleMustExistByIdOrError(newRoleDto.getId());
        if (!currentRole.getName().getValue().equals(newRoleDto.getName())) {
            this.newRoleNameDoesNotExistOrError(newRoleDto.getName());
        }

        currentRole.update(
                new RoleName(newRoleDto.getName()),
                new RoleDescription(newRoleDto.getDescription())
        );
        roleRepository.save(currentRole);
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
