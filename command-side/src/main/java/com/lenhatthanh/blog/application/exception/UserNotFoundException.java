package com.lenhatthanh.blog.application.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String code) {
        super(code);
    }
}
