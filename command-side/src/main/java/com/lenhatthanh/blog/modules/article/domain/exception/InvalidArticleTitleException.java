package com.lenhatthanh.blog.modules.article.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class InvalidArticleTitleException extends DomainException {
    public InvalidArticleTitleException() {
        super("DOMAIN-ERROR-0001");
    }
}
