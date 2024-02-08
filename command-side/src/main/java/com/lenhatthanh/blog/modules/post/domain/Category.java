package com.lenhatthanh.blog.modules.post.domain;

import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import lombok.Getter;

@Getter
public class Category extends AggregateRoot<Id> {
    private Id parentId;
    private Slug slug;
    private Title title;

    public Category(Id id, Long aggregateVersion, Id parentId, Title title, Slug slug) {
        super(id, aggregateVersion);
        this.parentId = parentId;
        this.title = title;
        this.slug = slug;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setParentId(Id parentId) {
        this.parentId = parentId;
    }

    public static Category create(Title title) {
        Id id = new Id(UniqueIdGenerator.create());
        Slug slug = new Slug(title);
        Long firstVersion = 0L;

        return new Category(id, firstVersion, null, title, slug);
    }
}
