package com.lenhatthanh.blog.modules.post.domain.service;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.post.application.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.post.domain.*;
import com.lenhatthanh.blog.modules.post.domain.repository.ArticleRepository;
import com.lenhatthanh.blog.modules.post.dto.ArticleDto;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.repository.UserRepository;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CreateArticleServiceImpl implements CreateArticleService {
    private ArticleRepository articleRepository;
    private UserRepository userRepository;

    @Override
    public void create(ArticleDto articleDto) {
        // In the microservice architecture,
        // We have `User` bounded context and `Article` bounded context.
        // That means we have two microservices for each bounded context.
        // So we can use Rest API (non-blocking) to get user information from `User` bounded context.
        Optional<User> user = userRepository.findById(articleDto.getUserId());
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        Article article = createArticleAggregate(user.get(), articleDto);
        articleRepository.save(article);
    }

    private Article createArticleAggregate(User user, ArticleDto articleDto) {
        Id articleId = new Id(UniqueIdGenerator.create());
        Title title = new Title(articleDto.getTitle());
        Summary summary = new Summary(articleDto.getSummary());
        ArticleContent content = new ArticleContent(articleDto.getContent());
        Slug slug = new Slug(articleDto.getSlug(), title);

        return Article.create(articleId, title, content, user.getId(), summary, articleDto.getThumbnail(), slug);
    }
}
