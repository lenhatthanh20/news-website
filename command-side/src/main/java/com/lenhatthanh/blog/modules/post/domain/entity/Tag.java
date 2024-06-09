package com.lenhatthanh.blog.modules.post.domain.entity;

import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.post.domain.valueobject.Slug;
import com.lenhatthanh.blog.modules.post.domain.valueobject.Title;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Tag extends AggregateRoot<Id> {
    private Title title;
    private Slug slug;

    public void setTitle(Title title) {
        this.title = title;
    }

    public static Tag create(Title title, Slug slug) {
        Tag tag = Tag.builder()
                .title(title)
                .slug(slug)
                .build();

        tag.setId(new Id(UniqueIdGenerator.create()));
        tag.setAggregateVersion(CONCURRENCY_CHECKING_INITIAL_VERSION);
        return tag;
    }
}
