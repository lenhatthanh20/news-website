package com.lenhatthanh.blog.modules.post.domain;

import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.core.domain.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Comment extends AggregateRoot<Id> {
    private Id parentId;
    private String content;
    private boolean isApproved;
    private LocalDateTime publishedAt;

    private Id userId;
    private Id postId;

    public Comment(
            Id id,
            Long aggregateVersion,
            Id parentId,
            String content,
            boolean isApproved,
            LocalDateTime publishedAt,
            Id userId,
            Id postId
    ) {
        super(id, aggregateVersion);
        this.parentId = parentId;
        this.content = content;
        this.isApproved = isApproved;
        this.publishedAt = publishedAt;
        this.userId = userId;
        this.postId = postId;
    }

    public static Comment create(Id id, Id parentId, String content, boolean isApproved, Id userId, Id postId) {
        Long firstVersion = 0L;
        LocalDateTime publishedAt = LocalDateTime.now();

        return new Comment(id, firstVersion, parentId, content, isApproved, publishedAt, userId, postId);
    }
}
