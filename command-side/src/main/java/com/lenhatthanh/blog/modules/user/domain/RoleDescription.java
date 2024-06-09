package com.lenhatthanh.blog.modules.user.domain;

import com.lenhatthanh.blog.modules.user.domain.exception.InvalidRoleDescriptionException;
import lombok.Getter;

@Getter
public class RoleDescription {
    private static final int MAX_LENGTH = 255;
    private static final int MIN_LENGTH = 3;

    private String value;

    public RoleDescription(String value) {
        setValue(value);
    }

    private void setValue(String value) {
        if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw new InvalidRoleDescriptionException();
        }

        this.value = value;
    }
}
