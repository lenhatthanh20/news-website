package com.lenhatthanh.blog.modules.user.domain;

import com.lenhatthanh.blog.modules.user.domain.exception.InvalidRoleDescriptionException;
import lombok.Getter;

@Getter
public class RoleDescription {
    private static final int MAX_LENGTH = 255;

    private String value;

    public RoleDescription(String value) {
        this.setValue(value);
    }

    private void setValue(String value) {
        if (value.length() > MAX_LENGTH) {
            throw new InvalidRoleDescriptionException();
        }

        this.value = value;
    }
}
