package com.lenhatthanh.blog.domain.exception;

public class InvalidArticleContentException extends RuntimeException {
    public InvalidArticleContentException(String message) {
        super(message);
    }
}
