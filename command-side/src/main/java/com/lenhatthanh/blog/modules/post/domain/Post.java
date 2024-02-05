package com.lenhatthanh.blog.modules.post.domain;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Post extends AggregateRoot<Id> {
//    private static final int MAX_COMMENT = 100;

    private Title title;
    private PostContent content;
    private Id userId;
    private Summary summary;
    private String thumbnail;
    private Slug slug;
    private List<Comment> comments = new ArrayList<>();
    private LocalDateTime publishedAt;

    public Post(Id id, Title title, PostContent content, Id userId, Summary summary, String thumbnail, Slug slug, Long aggregateVersion) {
        super(id, aggregateVersion);
        this.setTitle(title);
        this.setContent(content);
        this.setSlug(slug);
        this.userId = userId;
        this.summary = summary;
        this.thumbnail = thumbnail;
        this.publishedAt = LocalDateTime.now();
    }

    public Post(Id id, Title title, PostContent content, Id userId, Summary summary, String thumbnail, Slug slug, List<Comment> comments, LocalDateTime publishedAt, Long aggregateVersion) {
        super(id, aggregateVersion);
        this.setTitle(title);
        this.setContent(content);
        this.setSlug(slug);
        this.userId = userId;
        this.summary = summary;
        this.thumbnail = thumbnail;
        this.comments = comments;
        this.publishedAt = publishedAt;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setContent(PostContent content) {
        this.content = content;
    }

    private void setSlug(Slug slug) {
        this.slug = slug;
    }

    public static Post create(
            Id id,
            Title title,
            PostContent content,
            Id userId,
            Summary summary,
            String thumbnail,
            Slug slug
    ) {
        // True invariants here, example
        Long firstVersion = 0L;
        return new Post(id, title, content, userId, summary, thumbnail, slug, firstVersion);

        // @TODO register domain event PostCreatedEvent
    }

    public void addComment(String content, String userId) {
        // True invariants here, example
        // Total of comments must be less than 100
        // When the status of the post is DRAFT, can not add comment


//        if (this.comments.size() >= MAX_COMMENT) {
//            throw new CommentsLimitExceededException();
//        }

        Comment comment = new Comment(new Id(UniqueIdGenerator.create()),content, new Id(userId));
        this.comments.add(comment);

        // @TODO register domain event CommentAddedEvent
    }
}
