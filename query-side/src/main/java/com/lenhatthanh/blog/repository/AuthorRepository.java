package com.lenhatthanh.blog.repository;

import com.lenhatthanh.blog.model.Author;
import com.redis.om.spring.repository.RedisDocumentRepository;

import java.util.Optional;

public interface AuthorRepository extends RedisDocumentRepository<Author, String> {
    Iterable<Author> findByAuthorId(String authorId);
    Optional<Author> findOneByEmail(String email);
}
