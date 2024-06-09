package com.lenhatthanh.blog.modules.post.application.usecase.implement;

import com.lenhatthanh.blog.modules.post.application.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.post.application.repository.PostUserRepository;
import com.lenhatthanh.blog.modules.post.application.usecase.AddCommentUseCase;
import com.lenhatthanh.blog.modules.post.domain.Comment;
import com.lenhatthanh.blog.modules.post.domain.Post;
import com.lenhatthanh.blog.modules.post.domain.PostUser;
import com.lenhatthanh.blog.modules.post.domain.exception.PostNotFoundException;
import com.lenhatthanh.blog.modules.post.application.repository.CommentRepository;
import com.lenhatthanh.blog.modules.post.application.repository.PostRepository;
import com.lenhatthanh.blog.modules.post.dto.CommentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AddCommentUseCaseImpl implements AddCommentUseCase {
    private PostRepository postRepository;
    private PostUserRepository postUserRepository;
    private CommentRepository commentRepository;

    public void execute(String postId, CommentDto commentDto) {
        getUserOrError(commentDto.getUserId());
        getPostOrError(postId);
        Comment comment = Comment.create(commentDto);
        commentRepository.save(comment);
    }

    private void getUserOrError(String userId) {
        Optional<PostUser> user = postUserRepository.findById(userId);
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
