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

        roleIds.add(roleId);
    }

    public void removeRole(Id roleId) {
        roleIds.remove(roleId);
    }

    public void activate() {
        if (isActive == ACTIVATED) {
            throw new UserAlreadyActivatedException();
        }

        isActive = ACTIVATED;
    }

    public void deactivate() {
        if (isActive == DEACTIVATED) {
            throw new UserAlreadyDeactivatedException();
        }

        isActive = DEACTIVATED;
    }

    public void maskAsDeleted() {
        if (isDeleted == DELETED) {
            throw new UserAlreadyDeletedException();
        }

        isDeleted = DELETED;
    }
}
