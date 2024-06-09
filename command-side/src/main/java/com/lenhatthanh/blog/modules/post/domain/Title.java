package com.lenhatthanh.blog.modules.post.domain;

import com.lenhatthanh.blog.modules.post.domain.exception.InvalidTitleException;
import lombok.Getter;

@Getter
public class Title {
    private String value;

    public Title(String value) {
        setValue(value);
    }

    private void setValue(String value) {
        if (value.length() < 3 || value.length() > 100) {
            throw new InvalidTitleException();
        }

        this.value = value;
    }
}
