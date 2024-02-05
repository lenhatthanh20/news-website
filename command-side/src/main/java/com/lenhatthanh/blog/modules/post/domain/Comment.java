package com.lenhatthanh.blog.modules.post.domain;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.core.domain.Entity;
import lombok.Getter;

@Getter
public class Comment extends Entity<Id> {
    private String content;
    private Id userId;

    public Comment(Id id, String content, Id userId) {
        super(id);
        this.content = content;
        this.userId = userId;
    }

    public Comment create(Id id, String content, Id userId) {
        return new Comment(id, content, userId);
    }
}
