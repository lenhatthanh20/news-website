package com.lenhatthanh.blog.domain.exception;

public class InvalidArticleTitleException extends DomainException {
    public InvalidArticleTitleException(String code) {
        super(code);
    }
}
