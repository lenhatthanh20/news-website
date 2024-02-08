package com.lenhatthanh.blog.modules.post.domain;

import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import lombok.Getter;

@Getter
public class Tag extends AggregateRoot<Id> {
    private Title title;
    private Slug slug;

    public Tag(Id id, Long aggregateVersion, Title title, Slug slug) {
        super(id, aggregateVersion);
        this.title = title;
        this.slug = slug;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public static Tag create(Title title) {
        Id id = new Id(UniqueIdGenerator.create());
        Slug slug = new Slug(title);
        Long firstVersion = 0L;

        return new Tag(id, firstVersion, title, slug);
    }
}