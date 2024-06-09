package com.lenhatthanh.blog.modules.post.application.repository;

import com.lenhatthanh.blog.modules.post.domain.entity.Post;

import java.util.Optional;

public interface PostRepository {
    void save(Post post);

    Optional<Post> findById(String id);

    void delete(Post post);
}
