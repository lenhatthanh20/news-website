package com.lenhatthanh.blog.modules.article.application.usecase;

import com.lenhatthanh.blog.core.domain.AggregateId;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import com.lenhatthanh.blog.modules.article.application.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.article.domain.Article;

import com.lenhatthanh.blog.modules.article.domain.repository.ArticleRepositoryInterface;
import com.lenhatthanh.blog.modules.article.dto.ArticleDto;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.repository.UserRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CreateArticle {
    private ArticleRepositoryInterface articleRepository;
    private UserRepositoryInterface userRepository;

    public void execute(ArticleDto articleRequest) {
        // In the microservice architecture,
        // We have `User` bounded context and `Article` bounded context.
        // That means we have two microservices for each bounded context.
        // So we can use Rest API (non-blocking) to get user information from `User` bounded context.
        Optional<User> user = userRepository.findById(articleRequest.getUserId());
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        Article article = Article.create(
                new AggregateId(UniqueIdGenerator.create()),
                articleRequest.getTitle(),
                articleRequest.getContent(),
                user.get().getId().toString(),
                articleRequest.getSummary(),
                articleRequest.getThumbnail(),
                articleRequest.getSlug()
        );

        articleRepository.save(article);
    }
}
