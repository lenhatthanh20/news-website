package com.lenhatthanh.blog.modules.user.application.exception;

import com.lenhatthanh.blog.core.application.ApplicationException;

public class RoleNotFoundException extends ApplicationException {
    public RoleNotFoundException() {
        super("APPLICATION-ERROR-0002");
    }
}
