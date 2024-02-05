package com.lenhatthanh.blog.modules.post.infrastructure.repository;

import com.lenhatthanh.blog.modules.post.infrastructure.repository.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostJpaRepository extends JpaRepository<PostEntity, String> {
}
