package com.lenhatthanh.blog.modules.user.domain;

import com.lenhatthanh.blog.core.domain.AggregateId;
import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.modules.user.domain.event.RoleCreatedEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role extends AggregateRoot<AggregateId> {
    private String name;
    private String description;

    private Role(AggregateId id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public static Role create(AggregateId id, String name, String description) {
        Role role = new Role(id, name, description);
        role.registerEvent(new RoleCreatedEvent(role));

        return role;
    }
}
