package com.lenhatthanh.blog.modules.user.domain;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.modules.user.domain.exception.UserAlreadyActivatedException;
import com.lenhatthanh.blog.modules.user.domain.exception.UserAlreadyDeactivatedException;
import com.lenhatthanh.blog.modules.user.domain.exception.UserAlreadyDeletedException;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class User extends AggregateRoot<Id> {
    public static final Boolean ACTIVATED = true;
    public static final Boolean DEACTIVATED = false;
    public static final Boolean DELETED = true;
    public static final Boolean NOT_DELETED = false;
    private UserName name;
    private Email email;
    private MobilePhone mobilePhone;
    private String password;
    Boolean isActive;
    Boolean isDeleted;

    // Relationship with Role aggregate via id
    private List<Id> roleIds;

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
        if (roleIds.contains(roleId)) {
            return;
        }

        this.roleIds.add(roleId);
    }

    public void removeRole(Id roleId) {
        this.roleIds.remove(roleId);
    }

    public void activate() {
        if (this.isActive == ACTIVATED) {
            throw new UserAlreadyActivatedException();
        }

        this.isActive = ACTIVATED;
    }

    public void deactivate() {
        if (this.isActive == DEACTIVATED) {
            throw new UserAlreadyDeactivatedException();
        }

        this.isActive = DEACTIVATED;
    }

    public void maskAsDeleted() {
        if (this.isDeleted == DELETED) {
            throw new UserAlreadyDeletedException();
        }

        this.isDeleted = DELETED;
    }
}
