package com.lenhatthanh.blog.modules.post.domain.repository;

import com.lenhatthanh.blog.modules.post.domain.Tag;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository {
    void save(Tag tag);
}
