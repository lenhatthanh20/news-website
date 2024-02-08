package com.lenhatthanh.blog.modules.post.domain;

import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import lombok.Getter;

@Getter
public class Category extends AggregateRoot<Id> {
    private Slug slug;
    private String title;

    public Category(Id id, Long aggregateVersion, Slug slug, String name) {
        super(id, aggregateVersion);
        this.slug = slug;
        this.title = name;
    }

    public void setName(String name) {
        this.title = name;
    }

    public static Category create(String title) {
        Id id = new Id(UniqueIdGenerator.create());
        Slug slug = new Slug(new Title(title));
        return new Category(id, 0L, slug, title);
    }
}
