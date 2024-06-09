package com.lenhatthanh.blog.modules.user.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class UserAlreadyActivatedException extends DomainException {
    public UserAlreadyActivatedException() {
        super("DOMAIN-ERROR-0023");
    }
}
