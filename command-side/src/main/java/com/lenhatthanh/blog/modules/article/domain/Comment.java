package com.lenhatthanh.blog.modules.article.domain;

import com.lenhatthanh.blog.core.domain.AggregateId;
import com.lenhatthanh.blog.core.domain.Entity;
import lombok.Getter;

@Getter
public class Comment extends Entity<AggregateId> {
    private String content;
    private AggregateId authorId;
    private AggregateId articleId;

    public Comment(AggregateId id, String content, AggregateId authorId, AggregateId articleId) {
        super(id);
        this.content = content;
        this.authorId = authorId;
        this.articleId = articleId;
    }

    public Comment create(AggregateId id, String content, AggregateId authorId, AggregateId articleId) {
        return new Comment(id, content, authorId, articleId);
    }
}
