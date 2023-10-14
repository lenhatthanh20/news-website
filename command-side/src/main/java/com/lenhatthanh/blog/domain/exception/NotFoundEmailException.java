package com.lenhatthanh.blog.domain.exception;

public class NotFoundEmailException extends RuntimeException {
    public NotFoundEmailException(String message) {
        super(message);
    }
}
