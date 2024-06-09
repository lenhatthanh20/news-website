package com.lenhatthanh.blog.modules.post.infra.persistence;

import com.lenhatthanh.blog.modules.post.domain.*;
import com.lenhatthanh.blog.modules.post.application.repository.PostRepository;
import com.lenhatthanh.blog.modules.post.infra.persistence.entity.PostEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private PostJpaRepository postJpaRepository;

    @Override
    public void save(Post post) {
        PostEntity postEntity = PostEntity.fromDomainModel(post);
        postJpaRepository.save(postEntity);
    }

    @Override
    public Optional<Post> findById(String id) {
        Optional<PostEntity> postEntity = postJpaRepository.findById(id);
        return postEntity.map(PostEntity::toDomainModel);
    }

    @Override
    public void delete(Post post) {
        postJpaRepository.deleteById(post.getId().toString());
    }
}
