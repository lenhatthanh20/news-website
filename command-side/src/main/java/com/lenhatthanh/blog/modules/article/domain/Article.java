package com.lenhatthanh.blog.modules.article.domain;

import com.lenhatthanh.blog.core.domain.AggregateId;
import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Article extends AggregateRoot<AggregateId> {
    private Title title;
    private ArticleContent content;
    private AggregateId userId;
    private Summary summary;
    private String thumbnail;
    private Slug slug;
    private List<Comment> comments = new ArrayList<>();
    private LocalDateTime publishedAt;

    public Article(AggregateId id, Title title, ArticleContent content, AggregateId userId, Summary summary, String thumbnail, Slug slug) {
        super(id);
        this.setTitle(title);
        this.setContent(content);
        this.setSlug(slug);
        this.userId = userId;
        this.summary = summary;
        this.thumbnail = thumbnail;
        this.publishedAt = LocalDateTime.now();
    }

    public Article(AggregateId id, Title title, ArticleContent content, AggregateId userId, Summary summary, String thumbnail, Slug slug, List<Comment> comments, LocalDateTime publishedAt) {
        super(id);
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

    public void setContent(ArticleContent content) {
        this.content = content;
    }

    private void setSlug(Slug slug) {
        this.slug = slug;
    }

    public static Article create(AggregateId id, Title title, ArticleContent content, AggregateId userId, Summary summary, String thumbnail, Slug slug) {
        return new Article(id, title, content, userId, summary, thumbnail, slug);

        // @TODO register domain event ArticleCreatedEvent
    }

    public void addComment(String content, String userId) {
        Comment comment = new Comment(new AggregateId(UniqueIdGenerator.create()),content, new AggregateId(userId));
        this.comments.add(comment);
    }
}
