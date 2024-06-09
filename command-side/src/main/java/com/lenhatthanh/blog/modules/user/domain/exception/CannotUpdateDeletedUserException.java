package com.lenhatthanh.blog.modules.user.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class CannotUpdateDeletedUserException extends DomainException {
    public CannotUpdateDeletedUserException() {
        super("DOMAIN-ERROR-0025");
    }
}
