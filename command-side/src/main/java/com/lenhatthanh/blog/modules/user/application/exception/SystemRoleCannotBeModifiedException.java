package com.lenhatthanh.blog.modules.user.application.exception;

import com.lenhatthanh.blog.core.application.ApplicationException;

public class SystemRoleCannotBeModifiedException extends ApplicationException {
    public SystemRoleCannotBeModifiedException() {
        super("APPLICATION-ERROR-0003");
    }
}
