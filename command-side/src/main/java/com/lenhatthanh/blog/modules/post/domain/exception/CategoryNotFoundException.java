package com.lenhatthanh.blog.modules.post.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class CategoryNotFoundException extends DomainException {
    public CategoryNotFoundException() {
        super("DOMAIN-ERROR-0018");
    }
}
