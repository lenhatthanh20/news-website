package com.lenhatthanh.blog.modules.user.domain.service;

import com.lenhatthanh.blog.core.domain.AggregateId;
import com.lenhatthanh.blog.modules.user.domain.exception.RoleAlreadyExistException;
import com.lenhatthanh.blog.modules.user.domain.Role;
import com.lenhatthanh.blog.modules.user.domain.repository.RoleRepositoryInterface;
import com.lenhatthanh.blog.modules.user.dto.RoleDto;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateRoleService implements CreateRoleServiceInterface{
    RoleRepositoryInterface roleRepository;

    public void create(RoleDto roleDto) {
        this.roleDoesNotExistOrError(roleDto.getName());
        Role role = Role.create(
                new AggregateId(UniqueIdGenerator.create()),
                roleDto.getName(),
                roleDto.getDescription()
        );

        roleRepository.save(role);
    }

    public void createList(List<RoleDto> roleDtoList) {
        List<Role> roles = roleDtoList.stream().map(roleDto -> {
            this.roleDoesNotExistOrError(roleDto.getName());
            return Role.create(
                    new AggregateId(UniqueIdGenerator.create()),
                    roleDto.getName(),
                    roleDto.getDescription()
            );
        }).toList();

        roleRepository.saveAll(roles);
    }

    private void roleDoesNotExistOrError(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isPresent()) {
            throw new RoleAlreadyExistException();
        }
    }
}
