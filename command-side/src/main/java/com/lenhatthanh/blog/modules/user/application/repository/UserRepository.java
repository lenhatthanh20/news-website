package com.lenhatthanh.blog.modules.user.application.repository;

import com.lenhatthanh.blog.modules.user.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(String id);
    Optional<User> findByEmail(String email);
    void delete(User user);
}
