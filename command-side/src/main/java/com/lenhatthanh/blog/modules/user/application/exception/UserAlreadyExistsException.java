package com.lenhatthanh.blog.modules.user.application.exception;

import com.lenhatthanh.blog.core.application.ApplicationException;

public class UserAlreadyExistsException extends ApplicationException {
    public UserAlreadyExistsException() {
        super("APPLICATION-ERROR-0004");
    }
}
