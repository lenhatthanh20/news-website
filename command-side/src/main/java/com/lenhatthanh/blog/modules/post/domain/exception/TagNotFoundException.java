package com.lenhatthanh.blog.modules.post.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class TagNotFoundException extends DomainException {
    public TagNotFoundException() {
        super("DOMAIN-ERROR-0019");
    }
}
