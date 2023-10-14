package com.lenhatthanh.blog.domain.exception;

public class InvalidAuthorNameException extends RuntimeException {
    public InvalidAuthorNameException(String message) {
        super(message);
    }
}
