package com.lenhatthanh.blog.modules.post.domain;

import com.lenhatthanh.blog.modules.post.domain.exception.InvalidPostTitleException;
import lombok.Getter;

@Getter
public class Title {
    private String value;

    public Title(String value) {
        this.setValue(value);
    }

    private void setValue(String value) {
        if (value.length() < 3 || value.length() > 255) {
            throw new InvalidPostTitleException();
        }

        this.value = value;
    }
}
