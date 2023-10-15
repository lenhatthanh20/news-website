package com.lenhatthanh.blog.domain.exception;

public class InvalidArticleContentException extends DomainException {
    public InvalidArticleContentException(String code) {
        super(code);
    }
}
