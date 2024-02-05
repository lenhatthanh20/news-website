package com.lenhatthanh.blog.modules.user.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class InvalidMobilePhoneException extends DomainException {
    public InvalidMobilePhoneException() {
        super("DOMAIN-ERROR-0017");
    }
}
