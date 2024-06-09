package com.lenhatthanh.blog.modules.post.domain.valueobject;

import com.lenhatthanh.blog.modules.post.domain.exception.InvalidSlugException;
import lombok.Getter;

@Getter
public class Slug {
    private static final int MAX_SLUG_LENGTH = 255;
    private static final int MIN_SLUG_LENGTH = 3;
    private static final String SLUG_VALIDATION_REGEX = "^[a-z0-9-]+$";

    private final String value;

    public Slug(String slug) {
        validate(slug);
        value = slug;
    }

    private void validate(String slug) {
        if (slug.length() < MIN_SLUG_LENGTH || slug.length() > MAX_SLUG_LENGTH) {
            throw new InvalidSlugException();
        }

        if (!isSlugValid(slug)) {
            throw new InvalidSlugException();
        }
    }

    private boolean isSlugValid(String slug) {
        return slug.matches(SLUG_VALIDATION_REGEX);
    }
}
