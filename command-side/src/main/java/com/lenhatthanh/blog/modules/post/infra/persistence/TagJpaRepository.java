package com.lenhatthanh.blog.modules.post.infra.persistence;

import com.lenhatthanh.blog.modules.post.infra.persistence.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagJpaRepository extends JpaRepository<TagEntity, String> {
}
