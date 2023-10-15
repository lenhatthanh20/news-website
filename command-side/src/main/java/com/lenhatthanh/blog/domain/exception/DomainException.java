package com.lenhatthanh.blog.domain.exception;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
    private final String code;

    public DomainException(String code) {
        super((String) null);
        this.code = code;
    }
}
