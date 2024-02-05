package com.lenhatthanh.blog.modules.post.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class ArticleNotFoundException extends DomainException {
    public ArticleNotFoundException() {
        super("DOMAIN-ERROR-0013");
    }
}
