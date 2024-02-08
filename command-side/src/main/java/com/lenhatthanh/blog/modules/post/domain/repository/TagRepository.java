package com.lenhatthanh.blog.modules.post.domain.repository;

import com.lenhatthanh.blog.modules.post.domain.Tag;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository {
    void save(Tag tag);

    Optional<Tag> findById(String id);

    void delete(Tag tag);
}
