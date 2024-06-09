package com.lenhatthanh.blog.modules.post.application.repository;

import com.lenhatthanh.blog.modules.post.domain.entity.PostUser;

import java.util.Optional;

public interface PostUserRepository {
    Optional<PostUser> findById(String id);
}
