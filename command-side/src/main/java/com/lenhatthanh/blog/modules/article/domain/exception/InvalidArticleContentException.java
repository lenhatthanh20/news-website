package com.lenhatthanh.blog.modules.article.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class InvalidArticleContentException extends DomainException {
    public InvalidArticleContentException() {
        super("DOMAIN-ERROR-0002");
    }
}
