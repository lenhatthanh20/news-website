package com.lenhatthanh.blog.modules.user.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class InvalidRoleDescriptionException extends DomainException {
    public InvalidRoleDescriptionException() {
        super("DOMAIN-ERROR-0010");
    }
}
