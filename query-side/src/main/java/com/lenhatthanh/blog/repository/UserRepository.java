package com.lenhatthanh.blog.repository;

import com.lenhatthanh.blog.model.User;
import com.redis.om.spring.repository.RedisDocumentRepository;

import java.util.Optional;

public interface UserRepository extends RedisDocumentRepository<User, String> {
    Iterable<User> findByUserId(String userId);
    Optional<User> findOneByEmail(String email);
}
