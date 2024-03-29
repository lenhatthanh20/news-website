package com.lenhatthanh.blog.modules.post.infra.repository;

import com.lenhatthanh.blog.modules.post.domain.*;
import com.lenhatthanh.blog.modules.post.domain.repository.PostRepository;
import com.lenhatthanh.blog.modules.post.infra.repository.entity.PostEntity;
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
        this.postJpaRepository.save(postEntity);
    }

    @Override
    public Optional<Post> findById(String id) {
        Optional<PostEntity> postEntity = this.postJpaRepository.findById(id);
        return postEntity.map(PostEntity::toDomainModel);
    }

    @Override
    public void delete(Post post) {
        this.postJpaRepository.deleteById(post.getId().toString());
    }
}
