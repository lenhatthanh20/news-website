package com.lenhatthanh.blog.modules.user.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class InvalidEmailException extends DomainException {
    public InvalidEmailException() {
        super("DOMAIN-ERROR-0006");
    }
}
