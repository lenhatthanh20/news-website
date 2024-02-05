package com.lenhatthanh.blog.modules.post.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class PostNotFoundException extends DomainException {
    public PostNotFoundException() {
        super("DOMAIN-ERROR-0013");
    }
}
