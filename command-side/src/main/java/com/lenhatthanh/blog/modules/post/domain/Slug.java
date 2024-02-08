package com.lenhatthanh.blog.modules.post.domain;

import com.lenhatthanh.blog.modules.post.domain.exception.InvalidSlugException;
import lombok.Getter;

@Getter
public class Slug {
    private static final String SLUG_REGEX = "[^a-z0-9\\s]";
    private static final String EMPTY = "";
    private static final String SLUG_SEPARATOR_REGEX = "\\s+";
    private static final String SLUG_SEPARATOR = "-";

    private static final int MAX_SLUG_LENGTH = 255;
    private static final int MIN_SLUG_LENGTH = 3;

    private static final String SLUG_VALIDATION_REGEX = "^[a-z0-9-]+$";

    private final String value;

    public Slug(String slug, Title title) {
        if (slug == null || slug.isEmpty()) {
            slug = this.generateSlugFromTitle(title);
        }

        this.validate(slug);
        this.value = slug;
    }

    public Slug(Title title) {
        String slug = this.generateSlugFromTitle(title);
        this.validate(slug);
        this.value = slug;
    }

    private void validate(String slug) {
        if (slug.length() < MIN_SLUG_LENGTH || slug.length() > MAX_SLUG_LENGTH) {
            throw new InvalidSlugException();
        }

        if (!this.isSlugValid(slug)) {
            throw new InvalidSlugException();
        }
    }

    private String generateSlugFromTitle(Title title) {
        return title.getValue().toLowerCase().replaceAll(SLUG_REGEX, EMPTY).replaceAll(SLUG_SEPARATOR_REGEX, SLUG_SEPARATOR);
    }

    private boolean isSlugValid(String slug) {
        return slug.matches(SLUG_VALIDATION_REGEX);
    }
}
