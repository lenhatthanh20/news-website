package com.lenhatthanh.blog.modules.article.infrastructure.repository;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.article.domain.*;
import com.lenhatthanh.blog.modules.article.domain.repository.ArticleRepositoryInterface;
import com.lenhatthanh.blog.modules.article.infrastructure.repository.entity.ArticleEntity;
import com.lenhatthanh.blog.modules.article.infrastructure.repository.entity.CommentEntity;
import com.lenhatthanh.blog.modules.user.infrastructure.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        List<CommentEntity> commentEntities = new ArrayList<>();
        article.getComments().stream().map(
                comment -> {
                    UserEntity commentUser = new UserEntity();
                    commentUser.setId(comment.getUserId().toString());
                    return new CommentEntity(comment.getId().toString(), commentUser, comment.getContent());
                }
        ).forEach(commentEntities::add);

        commentEntities.forEach(commentEntity -> commentEntity.setArticle(articleEntity));
        articleEntity.setComments(commentEntities);

        this.articleJpaRepository.save(articleEntity);
    }

    @Override
    public Optional<Article> findById(String id) {
        Optional<ArticleEntity> articleEntity = this.articleJpaRepository.findById(id);
        if (articleEntity.isEmpty()) {
            return Optional.empty();
        }

        ArticleEntity article = articleEntity.get();
        List<CommentEntity> comments = article.getComments();
        List<Comment> commentList = new ArrayList<>();
        comments.stream().map(
                commentEntity -> new Comment(
                        new Id(commentEntity.getId()),
                        commentEntity.getContent(),
                        new Id(commentEntity.getUser().getId())
                )
        ).forEach(commentList::add);

        return Optional.of(
                new Article(
                        new Id(article.getId()),
                        new Title(article.getTitle()),
                        new ArticleContent(article.getContent()),
                        new Id(article.getUser().getId()),
                        new Summary(article.getSummary()),
                        article.getThumbnail(),
                        new Slug(article.getSlug(), new Title(article.getTitle())),
                        commentList,
                        article.getPublishedAt()
                )
        );
    }
}
