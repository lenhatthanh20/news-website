package com.lenhatthanh.blog.application.usecase;

import com.lenhatthanh.blog.domain.Role;
import com.lenhatthanh.blog.domain.repository.RoleRepositoryInterface;
import com.lenhatthanh.blog.dto.RoleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class CreateRole {
    private RoleRepositoryInterface roleRepository;

    public void execute(RoleDto roleDto) {
        Role role = new Role(
                UUID.randomUUID().toString(),
                roleDto.getName(),
                roleDto.getDescription()
        );

        roleRepository.save(role);
    }
}
