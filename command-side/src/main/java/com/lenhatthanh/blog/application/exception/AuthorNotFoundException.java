package com.lenhatthanh.blog.application.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String code) {
        super(code);
    }
}
