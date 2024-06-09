package com.lenhatthanh.blog.modules.user.application.exception;

import com.lenhatthanh.blog.core.application.ApplicationException;

public class RoleAlreadyExistException extends ApplicationException {
    public RoleAlreadyExistException() {
        super("APPLICATION-ERROR-0001");
    }
}
