package com.lenhatthanh.blog.modules.user.domain;

import com.lenhatthanh.blog.modules.user.domain.exception.InvalidUserNameException;
import lombok.Getter;

@Getter
public class UserName {
    private String value;

    private UserName(String value) {
        setValue(value);
    }

    private void setValue(String value) {
        if (value.length() < 3 || value.length() > 50) {
            throw new InvalidUserNameException();
        }

        this.value = value;
    }

    public static UserName create(String value) {
        return new UserName(value);
    }
}
