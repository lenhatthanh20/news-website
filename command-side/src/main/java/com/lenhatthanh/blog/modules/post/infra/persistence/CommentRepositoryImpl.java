package com.lenhatthanh.blog.modules.post.infra.persistence;

import com.lenhatthanh.blog.modules.post.domain.Comment;
import com.lenhatthanh.blog.modules.post.application.repository.CommentRepository;
import com.lenhatthanh.blog.modules.post.infra.persistence.entity.CommentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
    private final CommentJpaRepository commentJpaRepository;

    @Override
    public void save(Comment comment) {
        CommentEntity commentEntity = CommentEntity.fromDomainModel(comment);
        commentJpaRepository.save(commentEntity);
    }
}
