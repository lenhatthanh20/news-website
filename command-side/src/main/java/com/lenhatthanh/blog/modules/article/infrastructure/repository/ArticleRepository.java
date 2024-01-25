package com.lenhatthanh.blog.modules.article.infrastructure.repository;

import com.lenhatthanh.blog.modules.article.domain.Article;
import com.lenhatthanh.blog.modules.article.domain.repository.ArticleRepositoryInterface;
import com.lenhatthanh.blog.modules.article.infrastructure.repository.entity.ArticleEntity;
import com.lenhatthanh.blog.modules.user.infrastructure.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ArticleRepository implements ArticleRepositoryInterface {
    private ArticleJpaRepository articleJpaRepository;

    @Override
    public void save(Article article) {
        // I don't want to fetch all UserEntity from database
        UserEntity user = new UserEntity();
        user.setId(article.getUserId().toString());

        ArticleEntity articleEntity = new ArticleEntity(
                article.getId().toString(),
                article.getTitle().getValue(),
                article.getContent().getValue(),
                user,
                article.getSummary().getValue(),
                article.getThumbnail(),
                article.getSlug().getValue(),
                article.getPublishedAt()
        );

        this.articleJpaRepository.save(articleEntity);
    }
}
