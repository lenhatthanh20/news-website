package com.lenhatthanh.blog.modules.user.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class UserAlreadyDeletedException extends DomainException {
    public UserAlreadyDeletedException() {
        super("DOMAIN-ERROR-0022");
    }
}
