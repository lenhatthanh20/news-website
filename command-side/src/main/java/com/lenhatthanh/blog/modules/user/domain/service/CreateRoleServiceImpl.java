package com.lenhatthanh.blog.modules.user.domain.service;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.user.domain.RoleDescription;
import com.lenhatthanh.blog.modules.user.domain.RoleName;
import com.lenhatthanh.blog.modules.user.domain.exception.RoleAlreadyExistException;
import com.lenhatthanh.blog.modules.user.domain.Role;
import com.lenhatthanh.blog.modules.user.domain.repository.RoleRepository;
import com.lenhatthanh.blog.modules.user.dto.RoleDto;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreateRoleServiceImpl implements CreateRoleService {
    RoleRepository roleRepository;

    public void create(RoleDto roleDto) {
        this.roleDoesNotExistOrError(roleDto.getName());

        Role role = Role.create(
                new Id(UniqueIdGenerator.create()),
                new RoleName(roleDto.getName()),
                new RoleDescription(roleDto.getDescription())
        );

        roleRepository.save(role);
    }

    public void createList(Set<RoleDto> roleDtoList) {
        Set<Role> roles = roleDtoList.stream().map(roleDto -> {
            this.roleDoesNotExistOrError(roleDto.getName());
            return Role.create(
                    new Id(UniqueIdGenerator.create()),
                    new RoleName(roleDto.getName()),
                    new RoleDescription(roleDto.getDescription())
            );
        }).collect(Collectors.toSet());

        roleRepository.saveAll(roles);
    }

    private void roleDoesNotExistOrError(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isPresent()) {
            throw new RoleAlreadyExistException();
        }
    }
}
