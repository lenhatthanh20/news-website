package com.lenhatthanh.blog.modules.post.domain.service;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.post.application.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.post.domain.*;
import com.lenhatthanh.blog.modules.post.domain.exception.CategoryNotFoundException;
import com.lenhatthanh.blog.modules.post.domain.exception.TagNotFoundException;
import com.lenhatthanh.blog.modules.post.domain.repository.CategoryRepository;
import com.lenhatthanh.blog.modules.post.domain.repository.PostRepository;
import com.lenhatthanh.blog.modules.post.domain.repository.TagRepository;
import com.lenhatthanh.blog.modules.post.dto.PostDto;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.repository.UserRepository;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CreatePostServiceImpl implements CreatePostService {
    private PostRepository postRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private TagRepository tagRepository;

    @Override
    public void create(PostDto postDto) {
        // In the microservice architecture,
        // We have `User` bounded context and `Post` bounded context.
        // That means we have two microservices for each bounded context.
        // So we can use Rest API (non-blocking) to get user information from `User` bounded context.
        this.userExistOrError(postDto.getUserId());
        this.categoriesAndTagsExistOrError(postDto);

        Post post = Post.create(postDto);
        postRepository.save(post);
    }

    private void userExistOrError(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
    }

    private void categoriesAndTagsExistOrError(PostDto postDto) {
        postDto.getCategoryIds().forEach(categoryId -> {
            if (categoryRepository.findById(categoryId).isEmpty()) {
                throw new CategoryNotFoundException();
            }
        });

        postDto.getTagIds().forEach(tagId -> {
            if (tagRepository.findById(tagId).isEmpty()) {
                throw new TagNotFoundException();
            }
        });
    }
}
