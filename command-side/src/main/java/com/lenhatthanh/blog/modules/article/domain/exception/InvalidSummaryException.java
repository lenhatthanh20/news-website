package com.lenhatthanh.blog.modules.article.domain.exception;

import com.lenhatthanh.blog.core.domain.DomainException;

public class InvalidSummaryException extends DomainException {
    public InvalidSummaryException() {
        super("DOMAIN-ERROR-0012");
    }
}