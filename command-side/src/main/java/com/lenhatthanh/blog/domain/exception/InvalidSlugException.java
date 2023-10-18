package com.lenhatthanh.blog.domain.exception;

public class InvalidSlugException extends DomainException {
    public InvalidSlugException(String errorCode) {
        super(errorCode);
    }
}
