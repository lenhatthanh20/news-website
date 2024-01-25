package com.lenhatthanh.blog.modules.article.application.usecase;

import com.lenhatthanh.blog.modules.article.application.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.article.domain.*;
import com.lenhatthanh.blog.modules.article.domain.exception.ArticleNotFoundException;
import com.lenhatthanh.blog.modules.article.domain.repository.ArticleRepositoryInterface;
import com.lenhatthanh.blog.modules.article.dto.CommentDto;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.repository.UserRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AddCommentUseCase {
    private ArticleRepositoryInterface articleRepository;
    private UserRepositoryInterface userRepository;

    public void execute(String articleId, CommentDto commentDto) {
        // In the microservice architecture,
        // We have `User` bounded context and `Article` bounded context.
        // That means we have two microservices for each bounded context.
        // So we can use Rest API (non-blocking) to get user information from `User` bounded context.
        Optional<User> user = userRepository.findById(commentDto.getUserId());
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        Article article = getArticleOrError(articleId);
        article.addComment(commentDto.getContent(), user.get().getId().toString());
        articleRepository.save(article);
    }

    private Article getArticleOrError(String articleId) {
        Optional<Article> article = articleRepository.findById(articleId);
        if (article.isEmpty()) {
            throw new ArticleNotFoundException();
        }

        return article.get();
    }
}
