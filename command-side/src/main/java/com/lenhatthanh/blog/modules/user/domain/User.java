package com.lenhatthanh.blog.modules.user.domain;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.modules.user.domain.event.UserCreatedEvent;
import lombok.Getter;

import java.util.Set;

@Getter
public class User extends AggregateRoot<Id> {
    private UserName name;
    private Email email;
    private MobilePhone mobilePhone;
    private String password;
    Boolean isActive;

    // Relationship with Role aggregate via id
    private Set<Id> roleIds;

    public User(Id id, Long aggregateVersion, UserName name, Email email, MobilePhone mobilePhone, String password, Boolean isActive) {
        super(id, aggregateVersion);
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobilePhone = mobilePhone;
        this.isActive = isActive;
    }

    public void updateName(UserName name) {
        this.name = name;
    }

    public void updateEmail(Email email) {
        this.email = email;
    }

    public void updateMobilePhone(MobilePhone mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void addRole(Id roleId) {
        this.roleIds.add(roleId);
    }

    public void removeRole(Id roleId) {
        this.roleIds.remove(roleId);
    }

    public void activate() {
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public static User create(Id id, UserName name, Email email, MobilePhone mobilePhone, String password) {
        Long firstVersion = 0L;
        Boolean isActive = true;
        User user = new User(id, firstVersion, name, email, mobilePhone, password, isActive);

        // Add domain event
        user.registerEvent(new UserCreatedEvent(user));

        return user;
    }
}
