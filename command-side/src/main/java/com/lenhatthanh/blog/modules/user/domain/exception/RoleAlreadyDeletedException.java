package com.lenhatthanh.blog.modules.user.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class RoleAlreadyDeletedException extends DomainException {
    public RoleAlreadyDeletedException() {
        super("DOMAIN-ERROR-0026");
    }
}
