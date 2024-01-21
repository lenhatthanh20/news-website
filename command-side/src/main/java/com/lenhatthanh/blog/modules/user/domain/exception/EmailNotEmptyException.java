package com.lenhatthanh.blog.modules.user.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class EmailNotEmptyException extends DomainException {
    public EmailNotEmptyException() {
        super("DOMAIN-ERROR-0004");
    }
}

