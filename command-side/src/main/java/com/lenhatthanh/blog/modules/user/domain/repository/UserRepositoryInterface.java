package com.lenhatthanh.blog.modules.user.domain.repository;

import com.lenhatthanh.blog.modules.user.domain.User;

import java.util.Optional;

public interface UserRepositoryInterface {
    void save(User user);
    Optional<User> findById(String id);
    Optional<User> findByEmail(String email);
}