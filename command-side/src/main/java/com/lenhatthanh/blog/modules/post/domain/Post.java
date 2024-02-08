package com.lenhatthanh.blog.modules.post.domain;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.core.domain.AggregateRoot;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Post extends AggregateRoot<Id> {
    private Id parentId;
    private Title title;
    private String metaTitle;
    private PostContent content;
    private Id userId;
    private Summary summary;
    private String thumbnail;
    private Slug slug;
    private LocalDateTime publishedAt;

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
        this.setSlug(slug);
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
        // True invariants here, example
        Long firstVersion = 0L;
        LocalDateTime publishedAt = LocalDateTime.now();
        return new Post(id, firstVersion, parentId, title, metaTitle, content, userId, summary, thumbnail, slug, publishedAt);

        // @TODO register domain event PostCreatedEvent
    }

//    public void addComment(String content, String userId) {
        // True invariants here, example
        // Total of comments must be less than 100
        // When the status of the post is DRAFT, can not add comment


//        if (this.comments.size() >= MAX_COMMENT) {
//            throw new CommentsLimitExceededException();
//        }

//        Comment comment = new Comment(new Id(UniqueIdGenerator.create()), content, new Id(userId));
//        this.comments.add(comment);

        // @TODO register domain event CommentAddedEvent
//    }
}
