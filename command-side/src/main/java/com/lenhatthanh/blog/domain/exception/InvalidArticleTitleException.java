package com.lenhatthanh.blog.domain.exception;

public class InvalidArticleTitleException extends RuntimeException {
    public InvalidArticleTitleException(String message) {
        super(message);
    }
}
