package com.lenhatthanh.blog.modules.article.domain;

import com.lenhatthanh.blog.modules.article.domain.exception.InvalidSummaryException;
import lombok.Getter;

@Getter
public class Summary {
    public static final int MAX_LENGTH = 500;
    public static final int MIN_LENGTH = 3;

    private String value;

    public Summary(String value) {
        this.setValue(value);
    }

    private void setValue(String value) {
        if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw new InvalidSummaryException();
        }

        this.value = value;
    }
}
