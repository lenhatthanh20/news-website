package com.lenhatthanh.blog.modules.user.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class RoleAlreadyExistException extends DomainException {
    public RoleAlreadyExistException() {
        super("DOMAIN-ERROR-0007");
    }
}
