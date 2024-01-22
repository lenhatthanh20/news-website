package com.lenhatthanh.blog.modules.user.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class InvalidUserNameException extends DomainException {
    public InvalidUserNameException() {
        super("DOMAIN-ERROR-0003");
    }
}

