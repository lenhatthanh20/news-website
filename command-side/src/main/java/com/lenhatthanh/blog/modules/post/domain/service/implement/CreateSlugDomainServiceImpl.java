package com.lenhatthanh.blog.modules.post.domain.service.implement;

import com.lenhatthanh.blog.modules.post.domain.service.CreateSlugDomainService;
import com.lenhatthanh.blog.modules.post.domain.valueobject.Slug;
import org.springframework.stereotype.Component;

@Component
public class CreateSlugDomainServiceImpl implements CreateSlugDomainService {
    private static final String SLUG_REGEX = "[^a-z0-9\\s]";
    private static final String EMPTY = "";
    private static final String SLUG_SEPARATOR_REGEX = "\\s+";
    private static final String SLUG_SEPARATOR = "-";

    @Override
    public Slug createFromTitle(String title) {
        // Slug business logics here
        String slug = title.toLowerCase()
                .replaceAll(SLUG_REGEX, EMPTY)
                .replaceAll(SLUG_SEPARATOR_REGEX, SLUG_SEPARATOR);

        return new Slug(slug);
    }
}
