package com.lenhatthanh.blog.modules.post.domain.service;

import com.lenhatthanh.blog.modules.post.domain.valueobject.Slug;

public interface CreateSlugDomainService {
    Slug createFromTitle(String title);
}
