package com.lenhatthanh.blog.infrastructure.repository;

import com.lenhatthanh.blog.infrastructure.repository.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorJpaRepository extends JpaRepository<AuthorEntity, String> {
}
