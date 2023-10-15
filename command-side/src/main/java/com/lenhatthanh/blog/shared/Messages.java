package com.lenhatthanh.blog.shared;

import org.springframework.context.MessageSource;
import lombok.AllArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class Messages {
    final private MessageSource messageSource;

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }
}
