package com.lenhatthanh.blog.domain.repository;

import com.lenhatthanh.blog.domain.article.Author;

import java.util.Optional;

public interface AuthorRepositoryInterface {
    void save(Author author);
    Optional<Author> findById(String id);
}
