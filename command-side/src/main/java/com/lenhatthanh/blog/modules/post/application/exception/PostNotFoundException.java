package com.lenhatthanh.blog.modules.post.application.exception;

import com.lenhatthanh.blog.core.application.ApplicationException;

public class PostNotFoundException extends ApplicationException {
    public PostNotFoundException() {
        super("APPLICATION-ERROR-0007");
    }
}
