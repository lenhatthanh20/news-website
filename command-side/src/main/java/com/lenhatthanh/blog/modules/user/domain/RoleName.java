package com.lenhatthanh.blog.modules.user.domain;

import com.lenhatthanh.blog.modules.user.domain.exception.InvalidRoleNameException;
import lombok.Getter;

@Getter
public class RoleName {
    private String value;

    public RoleName(String value) {
        this.setValue(value);
    }

    private void setValue(String value) {
        if (!value.matches("^[a-zA-Z_]+$")) {
            throw new InvalidRoleNameException();
        }

        this.value = value;
    }
}
