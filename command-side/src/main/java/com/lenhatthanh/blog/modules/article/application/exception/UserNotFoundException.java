package com.lenhatthanh.blog.modules.article.application.exception;

import com.lenhatthanh.blog.core.application.ApplicationException;

public class UserNotFoundException extends ApplicationException {
    public UserNotFoundException() {
        super("APPLICATION-ERROR-0001");
    }
}
