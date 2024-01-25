package com.lenhatthanh.blog.modules.user.domain;

import com.lenhatthanh.blog.modules.user.domain.exception.InvalidRoleNameException;
import lombok.Getter;

@Getter
public class RoleName {
    private static final int MAX_LENGTH = 100;
    private static final int MIN_LENGTH = 3;

    private String value;

    public RoleName(String value) {
        this.setValue(value);
    }

    private void setValue(String value) {
        if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw new InvalidRoleNameException();
        }

        if (!value.matches("^[a-zA-Z_]+$")) {
            throw new InvalidRoleNameException();
        }

        this.value = value;
    }
}
