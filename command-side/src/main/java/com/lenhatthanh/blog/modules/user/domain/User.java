package com.lenhatthanh.blog.modules.user.domain;

import com.lenhatthanh.blog.core.domain.AggregateId;
import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.modules.user.domain.event.UserCreatedEvent;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class User extends AggregateRoot<AggregateId> {
    public int MAX_NAME_LENGTH = 50;
    public int MIN_NAME_LENGTH = 3;

    private UserName name;
    private Email email;
    private final String password;

    // Relationship with Role aggregate via id
    private final Set<AggregateId> roleIds = new HashSet<>();

    public User(AggregateId id, UserName name, Email email, String password) {
        super(id);
        this.setName(name);
        this.setEmail(email);
        this.password = password;
    }

    protected void setName(UserName name) {
        this.name = name;
    }

    protected void setEmail(Email email) {
        this.email = email;
    }

    public void addRole(AggregateId roleId) {
        if (roleIds.contains(roleId)) {
            return;
        }

        roleIds.add(roleId);
    }

    public void removeRole(AggregateId roleId) {
        if (!roleIds.contains(roleId)) {
            return;
        }

        roleIds.remove(roleId);
    }

    public static User create(AggregateId id, UserName name, Email email, String password) {
        User user = new User(id, name, email, password);

        // Add domain event
        user.registerEvent(new UserCreatedEvent(user));

        return user;
    }
}
