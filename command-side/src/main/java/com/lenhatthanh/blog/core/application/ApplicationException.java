package com.lenhatthanh.blog.core.application;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private final String code;

    public ApplicationException(String code) {
        super((String) null);
        this.code = code;
    }
}
