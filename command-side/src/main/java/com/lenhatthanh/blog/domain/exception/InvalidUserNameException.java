package com.lenhatthanh.blog.domain.exception;

public class InvalidUserNameException extends DomainException {
    public InvalidUserNameException(String code) {
        super(code);
    }
}
