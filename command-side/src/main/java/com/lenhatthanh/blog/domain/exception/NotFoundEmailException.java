package com.lenhatthanh.blog.domain.exception;

public class NotFoundEmailException extends DomainException {
    public NotFoundEmailException(String code) {
        super(code);
    }
}
