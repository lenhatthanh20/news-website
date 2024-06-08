package com.lenhatthanh.blog.modules.post.domain;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.modules.post.domain.event.PostCreatedEvent;
import com.lenhatthanh.blog.modules.post.domain.exception.CategoryLimitExceededException;
import com.lenhatthanh.blog.modules.post.domain.exception.TagLimitExceededException;
import com.lenhatthanh.blog.modules.post.dto.PostDto;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
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

    public static Post create(PostDto postDto) {
        Post post = Post.builder()
                .parentId(postDto.getParentId() != null ? new Id(postDto.getParentId()) : null)
                .userId(new Id(postDto.getUserId()))
                .title(new Title(postDto.getTitle()))
                .metaTitle(postDto.getMetaTitle())
                .content(new PostContent(postDto.getContent()))
                .summary(new Summary(postDto.getSummary()))
                .thumbnail(postDto.getThumbnail())
                .slug(new Slug(postDto.getSlug()))
                .build();

        post.setId(new Id(UniqueIdGenerator.create()));
        post.setAggregateVersion(CONCURRENCY_CHECKING_INITIAL_VERSION);
        post.setCategoryIds(postDto.getCategoryIds().stream().map(Id::new).toList());
        post.setTagIds(postDto.getTagIds().stream().map(Id::new).toList());

        post.registerEvent(new PostCreatedEvent(post));
        return post;
    }
}
