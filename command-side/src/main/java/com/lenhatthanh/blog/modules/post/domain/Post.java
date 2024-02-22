package com.lenhatthanh.blog.modules.post.domain;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.modules.post.domain.exception.CategoryLimitExceededException;
import com.lenhatthanh.blog.modules.post.domain.exception.TagLimitExceededException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
public class Post extends AggregateRoot<Id> {
    public static final int MAX_CATEGORIES = 5;
    public static final int MAX_TAGS = 10;

    private Id parentId;
    private Title title;
    private String metaTitle;
    private PostContent content;
    private Id userId;
    private Summary summary;
    private String thumbnail;
    private Slug slug;
    private LocalDateTime publishedAt;

    // Aggregate relationship via global ID
    private Set<Id> categoryIds = new HashSet<>();
    private Set<Id> tagIds = new HashSet<>();

    public Post(
            Id id,
            Long aggregateVersion,
            Id parentId,
            Title title,
            String metaTitle,
            PostContent content,
            Id userId,
            Summary summary,
            String thumbnail,
            Slug slug,
            LocalDateTime publishedAt
    ) {
        super(id, aggregateVersion);
        this.parentId = parentId;
        this.title = title;
        this.metaTitle = metaTitle;
        this.content = content;
        this.userId = userId;
        this.summary = summary;
        this.thumbnail = thumbnail;
        this.slug = slug;
        this.publishedAt = publishedAt;
    }

    public static Post create(
            Id id,
            Id parentId,
            Title title,
            String metaTitle,
            PostContent content,
            Id userId,
            Summary summary,
            String thumbnail,
            Slug slug
    ) {
        // True invariants here
        Long firstVersion = 0L;
        LocalDateTime publishedAt = LocalDateTime.now();
        return new Post(id, firstVersion, parentId, title, metaTitle, content, userId, summary, thumbnail, slug, publishedAt);

        // @TODO register domain event PostCreatedEvent
    }

    public void setCategoryIds(Set<Id> categoryIds) {
        if (categoryIds.size() > MAX_CATEGORIES) {
            throw new CategoryLimitExceededException();
        }

        this.categoryIds = categoryIds;
    }

    public void setTagIds(Set<Id> tagIds) {
        if (tagIds.size() > MAX_TAGS) {
            throw new TagLimitExceededException();
        }

        this.tagIds = tagIds;
    }
}
