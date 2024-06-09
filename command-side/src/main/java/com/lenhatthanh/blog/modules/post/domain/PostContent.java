package com.lenhatthanh.blog.modules.post.domain;

import com.lenhatthanh.blog.modules.post.domain.exception.InvalidPostContentException;
import lombok.Getter;

@Getter
public class PostContent {
    private static final int MAX_LENGTH = 20000;
    private static final int MIN_LENGTH = 3;

    private String value;

    public PostContent(String value) {
        setValue(value);
    }

    private void setValue(String value) {
        if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw new InvalidPostContentException();
        }

        this.value = value;
    }
}
