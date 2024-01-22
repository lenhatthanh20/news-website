package com.lenhatthanh.blog.modules.user.application.usecase;

import com.lenhatthanh.blog.core.domain.AggregateId;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import com.lenhatthanh.blog.modules.user.domain.Role;
import com.lenhatthanh.blog.modules.user.domain.repository.RoleRepositoryInterface;
import com.lenhatthanh.blog.modules.user.dto.RoleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateRole {
    private RoleRepositoryInterface roleRepository;

    public void execute(RoleDto roleDto) {
        Role role = Role.create(
                new AggregateId(UniqueIdGenerator.create()),
                roleDto.getName(),
                roleDto.getDescription()
        );

        roleRepository.save(role);
    }
}
