package com.lenhatthanh.blog.modules.post.domain.service;

import com.lenhatthanh.blog.modules.post.domain.Post;
import com.lenhatthanh.blog.modules.post.domain.exception.PostNotFoundException;
import com.lenhatthanh.blog.modules.post.domain.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletePostServiceImpl implements DeletePostService {
    private final PostRepository postRepository;

    @Override
    public void delete(String id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        postRepository.delete(post);
    }
}
