package com.lenhatthanh.blog.modules.user.domain.service.implement;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.core.domain.RoleStatus;
import com.lenhatthanh.blog.modules.user.domain.entity.Role;
import com.lenhatthanh.blog.modules.user.domain.event.RoleCreatedEvent;
import com.lenhatthanh.blog.modules.user.domain.event.RoleDeletedEvent;
import com.lenhatthanh.blog.modules.user.domain.service.RoleDomainService;
import com.lenhatthanh.blog.modules.user.domain.valueobject.RoleDescription;
import com.lenhatthanh.blog.modules.user.domain.valueobject.RoleName;
import com.lenhatthanh.blog.modules.user.dto.RoleDto;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import org.springframework.stereotype.Service;

import static com.lenhatthanh.blog.core.domain.AggregateRoot.CONCURRENCY_CHECKING_INITIAL_VERSION;

@Service
public class RoleDomainServiceImpl implements RoleDomainService {
    @Override
    public Role createNewRole(RoleDto roleDto) {
        Role role = Role.builder()
                .name(new RoleName(roleDto.getName()))
                .description(new RoleDescription(roleDto.getDescription()))
                .status(RoleStatus.ACTIVE)
                .build();
        role.setId(new Id(UniqueIdGenerator.create()));
        role.setAggregateVersion(CONCURRENCY_CHECKING_INITIAL_VERSION);
        role.registerEvent(new RoleCreatedEvent(role));
        return role;
    }

    @Override
    public void updateRole(String id, String name, String description) {
        // TODO: Implement update role
        return;
    }

    @Override
    public Role deleteRole(Role role) {
        role.delete();
        role.registerEvent(new RoleDeletedEvent(role));
        return role;
    }
}
