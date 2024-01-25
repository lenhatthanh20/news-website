package com.lenhatthanh.blog.modules.article.domain;

import com.lenhatthanh.blog.core.domain.AggregateId;
import com.lenhatthanh.blog.core.domain.AggregateRoot;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private List<Comment> comment;
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

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setContent(ArticleContent content) {
        this.content = content;
    }

    public void setSlug(Slug slug) {
        this.slug = slug;
    }

    public static Article create(AggregateId id, Title title, ArticleContent content, AggregateId userId, Summary summary, String thumbnail, Slug slug) {
        return new Article(id, title, content, userId, summary, thumbnail, slug);

        // @TODO register domain event ArticleCreatedEvent
    }
}
