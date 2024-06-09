package com.lenhatthanh.blog.modules.user.domain.entity;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.core.domain.RoleStatus;
import com.lenhatthanh.blog.modules.user.domain.event.RoleUpdatedEvent;
import com.lenhatthanh.blog.modules.user.domain.event.RoleDeletedEvent;
import com.lenhatthanh.blog.modules.user.domain.valueobject.RoleDescription;
import com.lenhatthanh.blog.modules.user.domain.valueobject.RoleName;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Role extends AggregateRoot<Id> {
    private RoleName name;
    private RoleDescription description;
    private RoleStatus status;

    public void updateRoleName(RoleName name) {
        this.name = name;
    }

    public void updateDescription(RoleDescription description) {
        this.description = description;
    }

    public void delete() {
        if (this.status == RoleStatus.DELETED) {
            throw new IllegalStateException("Role is already deleted");
        }
        this.status = RoleStatus.DELETED;
    }
}
