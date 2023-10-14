package com.lenhatthanh.blog.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorJpaRepository extends JpaRepository<AuthorEntity, String> {
}
