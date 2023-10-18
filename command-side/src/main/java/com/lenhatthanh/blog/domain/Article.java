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
    private Author author;
    private String summary;
    private String thumbnail;
    private Slug slug;
    private String createdAt;
    private String publishedAt;
    private String updatedAt;

    public Article(String id, String title, String content, Author author, String summary, String thumbnail, String slug, String createdAt, String publishedAt, String updatedAt) {
        super(id);
        this.setTitle(title);
        this.setContent(content);
        this.author = author;
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

    private String generateSlug(String title) {
        return title.toLowerCase().replaceAll("[^a-z0-9\\s]", "").replaceAll("\\s+", "-");
    }

    private boolean isSlugValid(String slug) {
        return slug.matches("^[a-z0-9-]+$");
    }
}
