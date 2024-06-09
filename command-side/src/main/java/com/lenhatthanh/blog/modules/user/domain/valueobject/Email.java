package com.lenhatthanh.blog.modules.user.domain.valueobject;

import com.lenhatthanh.blog.modules.user.domain.exception.EmailNotEmptyException;
import com.lenhatthanh.blog.modules.user.domain.exception.InvalidEmailException;
import lombok.Getter;

@Getter
public class Email {
    // Ref: https://owasp.org/www-community/OWASP_Validation_Regex_Repository
    private final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";

    private String value;

    public Email(String value) {
        setValue(value);
    }

    private void setValue(String value) {
        if (value == null || value.isEmpty()) {
            throw new EmailNotEmptyException();
        }

        if (!value.matches(emailRegex)) {
            throw new InvalidEmailException();
        }

        this.value = value;
    }
}
