package com.lenhatthanh.blog.application.usecase;

import com.lenhatthanh.blog.application.exception.UserNotFoundException;
import com.lenhatthanh.blog.domain.Article;
import com.lenhatthanh.blog.domain.User;
import com.lenhatthanh.blog.domain.repository.ArticleRepositoryInterface;
import com.lenhatthanh.blog.domain.repository.UserRepositoryInterface;
import com.lenhatthanh.blog.dto.ArticleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CreateArticle {
    private ArticleRepositoryInterface articleRepository;
    private UserRepositoryInterface userRepository;

    public void execute(ArticleDto articleRequest) {
        Optional<User> user = userRepository.findById(articleRequest.getUserId());
        if (user.isEmpty()) {
            throw new UserNotFoundException("APPLICATION-ERROR-0001");
        }

        Article article = new Article(
                UUID.randomUUID().toString(),
                articleRequest.getTitle(),
                articleRequest.getContent(),
                user.get().getId(),
                articleRequest.getSummary(),
                articleRequest.getThumbnail(),
                articleRequest.getSlug()
        );

        articleRepository.save(article);
    }
}
