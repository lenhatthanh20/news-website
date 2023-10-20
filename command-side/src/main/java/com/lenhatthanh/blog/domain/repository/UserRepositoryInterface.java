package com.lenhatthanh.blog.domain.repository;

import com.lenhatthanh.blog.domain.User;

import java.util.Optional;

public interface UserRepositoryInterface {
    void save(User user);
    Optional<User> findById(String id);
}
