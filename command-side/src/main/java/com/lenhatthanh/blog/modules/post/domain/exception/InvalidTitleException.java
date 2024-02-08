package com.lenhatthanh.blog.modules.post.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class InvalidTitleException extends DomainException {
    public InvalidTitleException() {
        super("DOMAIN-ERROR-0001");
    }
}
