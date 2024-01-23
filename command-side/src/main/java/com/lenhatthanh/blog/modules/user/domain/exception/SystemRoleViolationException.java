package com.lenhatthanh.blog.modules.user.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class SystemRoleViolationException extends DomainException {
    public SystemRoleViolationException() {
        super("DOMAIN-ERROR-0011");
    }
}
