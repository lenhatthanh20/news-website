package com.lenhatthanh.blog.modules.post.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class CategoryLimitExceededException extends DomainException {
    public CategoryLimitExceededException() {
        super("DOMAIN-ERROR-0020");
    }
}
