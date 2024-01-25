package com.lenhatthanh.blog.modules.user.domain;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.modules.user.domain.event.RoleCreatedEvent;
import com.lenhatthanh.blog.modules.user.domain.event.RoleUpdatedEvent;
import lombok.Getter;

@Getter
public class Role extends AggregateRoot<Id> {
    private RoleName name;
    private RoleDescription description;

    public Role(Id id, RoleName name, RoleDescription description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public void update(RoleName name, RoleDescription description) {
        this.name = name;
        this.description = description;

        this.registerEvent(new RoleUpdatedEvent(this));
    }

    public static Role create(Id id, RoleName name, RoleDescription description) {
        Role role = new Role(id, name, description);
        role.registerEvent(new RoleCreatedEvent(role));

        return role;
    }
}
