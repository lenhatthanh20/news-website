package com.lenhatthanh.blog.modules.user.domain;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.modules.user.domain.event.RoleCreatedEvent;
import com.lenhatthanh.blog.modules.user.domain.event.RoleUpdatedEvent;
import com.lenhatthanh.blog.modules.user.domain.event.RoleDeletedEvent;
import com.lenhatthanh.blog.modules.user.dto.RoleDto;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Role extends AggregateRoot<Id> {
    private RoleName name;
    private RoleDescription description;

    public void updateRoleName(RoleName name) {
        this.name = name;
        this.registerEvent(new RoleUpdatedEvent(this));
    }

    public void updateDescription(RoleDescription description) {
        this.description = description;
        this.registerEvent(new RoleUpdatedEvent(this));
    }

    public void delete() {
        this.registerEvent(new RoleDeletedEvent(this));
    }

    public static Role create(RoleDto roleDto) {
        Role role = Role.builder()
                .name(new RoleName(roleDto.getName()))
                .description(new RoleDescription(roleDto.getDescription()))
                .build();
        role.setId(new Id(UniqueIdGenerator.create()));
        role.setAggregateVersion(CONCURRENCY_CHECKING_INITIAL_VERSION);
        role.registerEvent(new RoleCreatedEvent(role));
        return role;
    }
}
