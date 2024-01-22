package com.lenhatthanh.blog.modules.user.application.usecase;

import com.lenhatthanh.blog.core.domain.AggregateId;
import com.lenhatthanh.blog.modules.user.application.exception.RoleAlreadyExistException;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import com.lenhatthanh.blog.modules.user.domain.Role;
import com.lenhatthanh.blog.modules.user.domain.repository.RoleRepositoryInterface;
import com.lenhatthanh.blog.modules.user.dto.RoleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CreateRole {
    private RoleRepositoryInterface roleRepository;

    public void execute(RoleDto roleDto) {
        this.roleDoesNotExistOrError(roleDto.getName());
        Role role = Role.create(
                new AggregateId(UniqueIdGenerator.create()),
                roleDto.getName(),
                roleDto.getDescription()
        );

        roleRepository.save(role);
    }

    private void roleDoesNotExistOrError(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isPresent()) {
            throw new RoleAlreadyExistException();
        }
    }
}
