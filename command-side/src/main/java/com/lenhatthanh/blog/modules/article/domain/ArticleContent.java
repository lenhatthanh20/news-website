package com.lenhatthanh.blog.modules.article.domain;

import com.lenhatthanh.blog.modules.article.domain.exception.InvalidArticleContentException;
import lombok.Getter;

@Getter
public class ArticleContent {
    private static final int MAX_LENGTH = 20000;
    private static final int MIN_LENGTH = 3;

    private String value;

    public ArticleContent(String value) {
        this.setValue(value);
    }

    private void setValue(String value) {
        if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw new InvalidArticleContentException();
        }

        this.value = value;
    }
}
