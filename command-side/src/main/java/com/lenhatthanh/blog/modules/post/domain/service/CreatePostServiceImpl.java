package com.lenhatthanh.blog.modules.post.domain.service;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.post.application.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.post.domain.*;
import com.lenhatthanh.blog.modules.post.domain.repository.PostRepository;
import com.lenhatthanh.blog.modules.post.dto.PostDto;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.repository.UserRepository;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CreatePostServiceImpl implements CreatePostService {
    private PostRepository postRepository;
    private UserRepository userRepository;

    @Override
    public void create(PostDto postDto) {
        // In the microservice architecture,
        // We have `User` bounded context and `Post` bounded context.
        // That means we have two microservices for each bounded context.
        // So we can use Rest API (non-blocking) to get user information from `User` bounded context.
        Optional<User> user = userRepository.findById(postDto.getUserId());
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        Post post = createPostAggregate(user.get(), postDto);
        postRepository.save(post);
    }

    private Post createPostAggregate(User user, PostDto postDto) {
        Id postId = new Id(UniqueIdGenerator.create());
        Title title = new Title(postDto.getTitle());
        Summary summary = new Summary(postDto.getSummary());
        PostContent content = new PostContent(postDto.getContent());
        Slug slug = new Slug(postDto.getSlug(), title);

        return Post.create(postId, title, content, user.getId(), summary, postDto.getThumbnail(), slug);
    }
}
