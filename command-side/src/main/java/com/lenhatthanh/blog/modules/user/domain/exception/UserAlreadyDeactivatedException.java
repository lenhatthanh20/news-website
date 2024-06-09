package com.lenhatthanh.blog.modules.user.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class UserAlreadyDeactivatedException extends DomainException {
    public UserAlreadyDeactivatedException() {
        super("DOMAIN-ERROR-0024");
    }
}
