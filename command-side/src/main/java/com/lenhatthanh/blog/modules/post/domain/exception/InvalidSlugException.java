package com.lenhatthanh.blog.modules.post.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class InvalidSlugException extends DomainException {
    public InvalidSlugException() {
        super("DOMAIN-ERROR-0005");
    }
}
