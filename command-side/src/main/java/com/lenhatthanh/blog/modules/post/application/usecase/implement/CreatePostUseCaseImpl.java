package com.lenhatthanh.blog.modules.post.application.usecase.implement;

import com.lenhatthanh.blog.modules.post.application.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.post.application.repository.PostUserRepository;
import com.lenhatthanh.blog.modules.post.application.usecase.CreatePostUseCase;
import com.lenhatthanh.blog.modules.post.domain.Post;
import com.lenhatthanh.blog.modules.post.domain.PostUser;
import com.lenhatthanh.blog.modules.post.domain.exception.CategoryNotFoundException;
import com.lenhatthanh.blog.modules.post.domain.exception.TagNotFoundException;
import com.lenhatthanh.blog.modules.post.application.repository.CategoryRepository;
import com.lenhatthanh.blog.modules.post.application.repository.PostRepository;
import com.lenhatthanh.blog.modules.post.application.repository.TagRepository;
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

    public void execute(PostDto postDto) {
        this.userExistOrError(postDto.getUserId());
        this.categoriesAndTagsExistOrError(postDto);
        //TODO: Business logic: Post slug must be unique, user role must be AUTHOR

        Post post = Post.create(postDto);
        postRepository.save(post);
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
}
