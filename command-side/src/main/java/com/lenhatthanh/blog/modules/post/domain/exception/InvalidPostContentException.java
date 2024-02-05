package com.lenhatthanh.blog.modules.post.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class InvalidPostContentException extends DomainException {
    public InvalidPostContentException() {
        super("DOMAIN-ERROR-0002");
    }
}
