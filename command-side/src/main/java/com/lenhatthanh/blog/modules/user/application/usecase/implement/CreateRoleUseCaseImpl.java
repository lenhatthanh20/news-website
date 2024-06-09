package com.lenhatthanh.blog.modules.user.application.usecase.implement;

import com.lenhatthanh.blog.modules.user.application.evenpublisher.RoleEventPublisher;
import com.lenhatthanh.blog.modules.user.application.usecase.CreateRoleUseCase;
import com.lenhatthanh.blog.modules.user.domain.entity.Role;
import com.lenhatthanh.blog.modules.user.application.exception.RoleAlreadyExistException;
import com.lenhatthanh.blog.modules.user.application.repository.RoleRepository;
import com.lenhatthanh.blog.modules.user.domain.service.RoleDomainService;
import com.lenhatthanh.blog.modules.user.dto.RoleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CreateRoleUseCaseImpl implements CreateRoleUseCase {
    private RoleRepository roleRepository;
    private RoleDomainService roleDomainService;
    private RoleEventPublisher publisher;

    public void execute(RoleDto roleDto) {
        // TODO: Check admin user
        roleNameDoesNotExistOrError(roleDto.getName());
        Role role = roleDomainService.createNewRole(roleDto);
        roleRepository.save(role);
        publishDomainEvents(role);
    }

    private void roleNameDoesNotExistOrError(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isPresent()) {
            throw new RoleAlreadyExistException();
        }
    }

    private void publishDomainEvents(Role role) {
        role.getDomainEvents().forEach(event -> publisher.publish(event));
    }
}
