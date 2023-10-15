package com.lenhatthanh.blog.domain.exception;

public class InvalidAuthorNameException extends DomainException {
    public InvalidAuthorNameException(String code) {
        super(code);
    }
}
