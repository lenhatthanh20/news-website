package com.lenhatthanh.blog.domain;

import com.lenhatthanh.blog.domain.exception.InvalidArticleContentException;
import com.lenhatthanh.blog.domain.exception.InvalidArticleTitleException;
import lombok.Getter;

@Getter
public class Article extends Entity<String> {
    public int MAX_TITLE_LENGTH = 255;
    public int MAX_CONTENT_LENGTH = 20000;

    private String title;
    private String content;
    private Author author;

    public Article(String id, String title, String content, Author author) {
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

    public void setAuthor(Author author) {
        this.author = author;
    }
}
