package com.lenhatthanh.blog.modules.post.application.usecase.implement;

import com.lenhatthanh.blog.modules.post.application.eventpublisher.PostEventPublisher;
import com.lenhatthanh.blog.modules.post.application.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.post.application.repository.PostUserRepository;
import com.lenhatthanh.blog.modules.post.application.usecase.CreatePostUseCase;
import com.lenhatthanh.blog.modules.post.domain.entity.Post;
import com.lenhatthanh.blog.modules.post.domain.entity.PostUser;
import com.lenhatthanh.blog.modules.post.application.exception.CategoryNotFoundException;
import com.lenhatthanh.blog.modules.post.application.exception.TagNotFoundException;
import com.lenhatthanh.blog.modules.post.application.repository.CategoryRepository;
import com.lenhatthanh.blog.modules.post.application.repository.PostRepository;
import com.lenhatthanh.blog.modules.post.application.repository.TagRepository;
import com.lenhatthanh.blog.modules.post.domain.service.PostDomainService;
import com.lenhatthanh.blog.modules.post.dto.PostDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CreatePostUseCaseImpl implements CreatePostUseCase {
    private PostRepository postRepository;
    private PostUserRepository postUserRepository;
    private CategoryRepository categoryRepository;
    private TagRepository tagRepository;
    private PostDomainService postDomainService;
    private PostEventPublisher publisher;

    public void execute(PostDto postDto) {
        // This step can be ignored because we are working in logged-in user context
        userExistOrError(postDto.getUserId());
        categoriesAndTagsExistOrError(postDto);
        //TODO: Business logic: Post slug must be unique, user role checking, etc.

        Post post = postDomainService.createNewPost(postDto);
        //TODO: Handle transaction - unit of work for aggregate
        postRepository.save(post);
        publishDomainEvents(post);
    }

    private void userExistOrError(String userId) {
        Optional<PostUser> user = postUserRepository.findById(userId);
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

    private void publishDomainEvents(Post post) {
        post.getDomainEvents().forEach(event -> publisher.publish(event));
    }
}
