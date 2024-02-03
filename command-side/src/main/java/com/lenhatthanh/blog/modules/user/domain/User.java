package com.lenhatthanh.blog.modules.user.domain;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.modules.user.domain.event.UserCreatedEvent;
import lombok.Getter;

@Getter
public class User extends AggregateRoot<Id> {
    private UserName name;
    private Email email;
    private final String password;

    // Relationship with Role aggregate via id
    private Id roleId;

    public User(Id id, UserName name, Email email, String password, Long aggregateVersion) {
        super(id, aggregateVersion);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    protected void updateName(UserName name) {
        this.name = name;
    }

    protected void updateEmail(Email email) {
        this.email = email;
    }

    public void changeRole(Id roleId) {
        this.roleId = roleId;
    }

    public static User create(Id id, UserName name, Email email, String password) {
        Long firstVersion = 0L;
        User user = new User(id, name, email, password, firstVersion);

        // Add domain event
        user.registerEvent(new UserCreatedEvent(user));

        return user;
    }
}
