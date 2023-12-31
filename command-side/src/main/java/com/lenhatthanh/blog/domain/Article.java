package com.lenhatthanh.blog.domain;

import com.lenhatthanh.blog.domain.exception.InvalidArticleContentException;
import com.lenhatthanh.blog.domain.exception.InvalidArticleTitleException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article extends Entity<String> {
    public int MAX_TITLE_LENGTH = 255;
    public int MAX_CONTENT_LENGTH = 20000;

    private String title;
    private String content;
    private String userId;
    private String summary;
    private String thumbnail;
    private Slug slug;

    public Article(String id, String title, String content, String userId, String summary, String thumbnail, String slug) {
        super(id);
        this.setTitle(title);
        this.setContent(content);
        this.setSlug(slug, title);
        this.userId = userId;
        this.summary = summary;
        this.thumbnail = thumbnail;
    }

    public void setTitle(String title) {
        if (title.length() > MAX_TITLE_LENGTH) {
            throw new InvalidArticleTitleException("DOMAIN-ERROR-0001");
        }

        this.title = title;
    }

    public void setContent(String content) {
        if (content.length() > MAX_CONTENT_LENGTH) {
            throw new InvalidArticleContentException("DOMAIN-ERROR-0002");
        }

        this.content = content;
    }

    public void setSlug(String slug, String title) {
        this.slug = new Slug(slug, title);
    }
}
