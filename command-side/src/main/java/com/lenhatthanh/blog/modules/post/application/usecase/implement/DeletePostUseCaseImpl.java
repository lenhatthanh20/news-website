package com.lenhatthanh.blog.modules.post.application.usecase.implement;

import com.lenhatthanh.blog.modules.post.application.usecase.DeletePostUseCase;
import com.lenhatthanh.blog.modules.post.domain.entity.Post;
import com.lenhatthanh.blog.modules.post.domain.exception.PostNotFoundException;
import com.lenhatthanh.blog.modules.post.application.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletePostUseCaseImpl implements DeletePostUseCase {
    private final PostRepository postRepository;

    public void execute(String id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        postRepository.delete(post);
    }
}
