package com.lenhatthanh.blog.modules.post.application.exception;

import com.lenhatthanh.blog.core.application.ApplicationException;

public class TagNotFoundException extends ApplicationException {
    public TagNotFoundException() {
        super("APPLICATION-ERROR-0008");
    }
}
