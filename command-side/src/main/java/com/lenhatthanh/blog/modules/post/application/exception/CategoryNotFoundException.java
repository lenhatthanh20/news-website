package com.lenhatthanh.blog.modules.post.application.exception;

import com.lenhatthanh.blog.core.application.ApplicationException;

public class CategoryNotFoundException extends ApplicationException {
    public CategoryNotFoundException() {
        super("APPLICATION-ERROR-0006");
    }
}
