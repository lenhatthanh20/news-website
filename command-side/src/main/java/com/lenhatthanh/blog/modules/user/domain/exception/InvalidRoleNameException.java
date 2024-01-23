package com.lenhatthanh.blog.modules.user.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class InvalidRoleNameException extends DomainException {
    public InvalidRoleNameException() {
        super("DOMAIN-ERROR-0009");
    }
}
