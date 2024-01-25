package com.lenhatthanh.blog.modules.article.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class ArticleNotFoundException extends DomainException {
    public ArticleNotFoundException() {
        super("DOMAIN-ERROR-0013");
    }
}
