package com.lenhatthanh.blog.modules.post.application.usecase.implement;

import com.lenhatthanh.blog.modules.post.application.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.post.application.usecase.AddCommentUseCase;
import com.lenhatthanh.blog.modules.post.domain.Comment;
import com.lenhatthanh.blog.modules.post.domain.Post;
import com.lenhatthanh.blog.modules.post.domain.exception.PostNotFoundException;
import com.lenhatthanh.blog.modules.post.domain.repository.CommentRepository;
import com.lenhatthanh.blog.modules.post.domain.repository.PostRepository;
import com.lenhatthanh.blog.modules.post.dto.CommentDto;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AddCommentUseCaseImpl implements AddCommentUseCase {
    private PostRepository postRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;

    public void execute(String postId, CommentDto commentDto) {
        // In the microservice architecture,
        // We have `User` bounded context and `Post` bounded context.
        // That means we have two microservices for each bounded context.
        // So we can use Rest API (can be non-blocking) to get user information from `User` bounded context.
        this.getUserOrError(commentDto.getUserId());
        this.getPostOrError(postId);
        Comment comment = Comment.create(commentDto);
        commentRepository.save(comment);
    }

    private void getUserOrError(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
    }

    private void getPostOrError(String postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new PostNotFoundException();
        }
    }
}
