package com.lenhatthanh.blog.modules.post.domain.entity;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.modules.post.domain.valueobject.PostContent;
import com.lenhatthanh.blog.modules.post.domain.valueobject.Slug;
import com.lenhatthanh.blog.modules.post.domain.valueobject.Summary;
import com.lenhatthanh.blog.modules.post.domain.valueobject.Title;
import com.lenhatthanh.blog.modules.post.domain.exception.CategoryLimitExceededException;
import com.lenhatthanh.blog.modules.post.domain.exception.TagLimitExceededException;
import com.lenhatthanh.blog.modules.post.domain.valueobject.PostStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class Post extends AggregateRoot<Id> {
    public static final int MAX_CATEGORIES = 5;
    public static final int MAX_TAGS = 10;

    private Id parentId;
    private Title title;
    private String metaTitle;
    private PostContent content;
    private Summary summary;
    private String thumbnail;
    private Slug slug;
    private LocalDateTime publishedAt;
    private PostStatus status;

    // Aggregate relationship via global ID
    private Id userId;
    private List<Id> categoryIds;
    private List<Id> tagIds;

    public void setUserId(Id userId) {
        this.userId = userId;
    }

    public void setCategoryIds(List<Id> categoryIds) {
        if (categoryIds.size() > MAX_CATEGORIES) {
            throw new CategoryLimitExceededException();
        }

        this.categoryIds = categoryIds;
    }

    public void setTagIds(List<Id> tagIds) {
        if (tagIds.size() > MAX_TAGS) {
            throw new TagLimitExceededException();
        }

        this.tagIds = tagIds;
    }
}
