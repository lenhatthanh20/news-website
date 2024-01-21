package com.lenhatthanh.blog.core.domain;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
    private final String code;

    public DomainException(String code) {
        super((String) null);
        this.code = code;
    }
}