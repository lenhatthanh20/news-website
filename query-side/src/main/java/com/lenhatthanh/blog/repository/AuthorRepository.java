package com.lenhatthanh.blog.repository;

import com.lenhatthanh.blog.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, String> {
}