package com.lenhatthanh.blog.modules.post.domain;

import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Category extends AggregateRoot<Id> {
    private Id parentId;
    private Slug slug;
    private Title title;

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setParentId(Id parentId) {
        this.parentId = parentId;
    }

    public static Category create(Title title, Slug slug, Id parentId) {
        Category category = Category.builder()
                .parentId(parentId)
                .title(title)
                .slug(slug)
                .build();

        category.setId(new Id(UniqueIdGenerator.create()));
        category.setAggregateVersion(CONCURRENCY_CHECKING_INITIAL_VERSION);
        return category;
    }
}
