package com.lenhatthanh.blog.modules.user.domain;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.modules.user.domain.event.UserCreatedEvent;
import com.lenhatthanh.blog.modules.user.domain.event.UserUpdatedEvent;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

@Getter
public class User extends AggregateRoot<Id> {
    private UserName name;
    private Email email;
    private MobilePhone mobilePhone;
    private String password;
    Boolean isActive;

    // Relationship with Role aggregate via id
    private Set<Id> roleIds = new HashSet<>();

    public User(Id id, Long aggregateVersion, UserName name, Email email, MobilePhone mobilePhone, String password, Boolean isActive, Set<Id> roleIds) {
        super(id, aggregateVersion);
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobilePhone = mobilePhone;
        this.isActive = isActive;
        this.roleIds = roleIds;
    }

    private void update(Consumer<User> fieldUpdater) {
        fieldUpdater.accept(this);
        this.registerEvent(new UserUpdatedEvent(this));
    }

    public void updateName(UserName name) {
        update(user -> user.name = name);
    }

    public void updateEmail(Email email) {
        update(user -> user.email = email);
    }

    public void updateMobilePhone(MobilePhone mobilePhone) {
        update(user -> user.mobilePhone = mobilePhone);
    }

    public void addRole(Id roleId) {
        update(user -> user.roleIds.add(roleId));
    }

    public void removeRole(Id roleId) {
        update(user -> user.roleIds.remove(roleId));
    }

    public void activate() {
        update(user -> user.isActive = true);
    }

    public void deactivate() {
        update(user -> user.isActive = false);
    }

    public static User create(Id id, UserName name, Email email, MobilePhone mobilePhone, String password, Id roleId) {
        Long firstVersion = 0L;
        Boolean isActive = true;
        Set<Id> roleIds = new HashSet<>();
        roleIds.add(roleId);
        User user = new User(id, firstVersion, name, email, mobilePhone, password, isActive, roleIds);

        // Add domain event
        user.registerEvent(new UserCreatedEvent(user));

        return user;
    }
}
