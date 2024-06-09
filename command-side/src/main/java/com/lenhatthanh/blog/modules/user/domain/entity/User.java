package com.lenhatthanh.blog.modules.user.domain.entity;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.core.domain.UserActivated;
import com.lenhatthanh.blog.core.domain.UserDeleted;
import com.lenhatthanh.blog.modules.user.domain.exception.UserAlreadyActivatedException;
import com.lenhatthanh.blog.modules.user.domain.exception.UserAlreadyDeactivatedException;
import com.lenhatthanh.blog.modules.user.domain.exception.UserAlreadyDeletedException;
import com.lenhatthanh.blog.modules.user.domain.valueobject.Email;
import com.lenhatthanh.blog.modules.user.domain.valueobject.MobilePhone;
import com.lenhatthanh.blog.modules.user.domain.valueobject.UserName;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class User extends AggregateRoot<Id> {
    private UserName name;
    private Email email;
    private MobilePhone mobilePhone;
    private String password;
    UserActivated isActive;
    UserDeleted isDeleted;

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
        if (isActive == UserActivated.TRUE) {
            throw new UserAlreadyActivatedException();
        }

        isActive = UserActivated.TRUE;
    }

    public void deactivate() {
        if (isActive == UserActivated.FALSE) {
            throw new UserAlreadyDeactivatedException();
        }

        isActive = UserActivated.FALSE;
    }

    public void maskAsDeleted() {
        if (isDeleted == UserDeleted.TRUE) {
            throw new UserAlreadyDeletedException();
        }

        isDeleted = UserDeleted.TRUE;
    }
}
