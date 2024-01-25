package com.lenhatthanh.blog.modules.article.domain;

import com.lenhatthanh.blog.core.domain.AggregateId;
import com.lenhatthanh.blog.core.domain.Entity;
import lombok.Getter;

@Getter
public class Comment extends Entity<AggregateId> {
    private String content;
    private AggregateId userId;

    public Comment(AggregateId id, String content, AggregateId userId) {
        super(id);
        this.content = content;
        this.userId = userId;
    }

    public Comment create(AggregateId id, String content, AggregateId userId) {
        return new Comment(id, content, userId);
    }
}
