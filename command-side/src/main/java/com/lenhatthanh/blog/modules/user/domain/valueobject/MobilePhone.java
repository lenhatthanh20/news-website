package com.lenhatthanh.blog.modules.user.domain.valueobject;

import com.lenhatthanh.blog.modules.user.domain.exception.InvalidMobilePhoneException;
import lombok.Getter;

@Getter
public class MobilePhone {
    private final String mobilePhoneRegex
            = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";

    private String value;

    public MobilePhone(String value) {
        setValue(value);
    }

    private void setValue(String value) {
        if (!value.matches(mobilePhoneRegex)) {
            throw new InvalidMobilePhoneException();
        }

        this.value = value;
    }
}
