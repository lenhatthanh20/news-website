package com.lenhatthanh.blog.modules.post.infra.repository;

import com.lenhatthanh.blog.modules.post.infra.repository.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentJpaRepository extends JpaRepository<CommentEntity, String> {
}
