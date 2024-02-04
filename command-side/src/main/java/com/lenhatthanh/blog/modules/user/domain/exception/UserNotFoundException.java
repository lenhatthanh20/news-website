package com.lenhatthanh.blog.modules.user.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class UserNotFoundException extends DomainException {
    public UserNotFoundException() {
        super("DOMAIN-ERROR-0016");
    }
}
