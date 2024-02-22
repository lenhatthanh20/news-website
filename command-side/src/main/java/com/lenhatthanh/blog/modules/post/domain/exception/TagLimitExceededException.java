package com.lenhatthanh.blog.modules.post.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class TagLimitExceededException extends DomainException {
    public TagLimitExceededException() {
        super("DOMAIN-ERROR-0021");
    }
}
