package com.lenhatthanh.blog.modules.post.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class InvalidPostTitleException extends DomainException {
    public InvalidPostTitleException() {
        super("DOMAIN-ERROR-0001");
    }
}
