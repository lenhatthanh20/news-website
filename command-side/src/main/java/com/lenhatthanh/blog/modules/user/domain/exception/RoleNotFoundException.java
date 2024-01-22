package com.lenhatthanh.blog.modules.user.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class RoleNotFoundException extends DomainException {
    public RoleNotFoundException() {
        super("DOMAIN-ERROR-0008");
    }
}
