package com.lenhatthanh.blog.modules.post.infra.repository;

import com.lenhatthanh.blog.modules.post.domain.Comment;
import com.lenhatthanh.blog.modules.post.domain.repository.CommentRepository;
import com.lenhatthanh.blog.modules.post.infra.repository.entity.CommentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
    private final CommentJpaRepository commentJpaRepository;

    @Override
    public void save(Comment comment) {
        CommentEntity commentEntity = new CommentEntity(
                comment.getId().toString(),
                comment.getAggregateVersion(),
                comment.getContent(),
                comment.isApproved(),
                comment.getPublishedAt()
        );

        if (commentEntity.getParentId() != null) {
            commentEntity.setParentId(comment.getParentId().toString());
        }

        commentEntity.setUserId(comment.getUserId().toString());
        commentEntity.setPostId(comment.getPostId().toString());
        commentJpaRepository.save(commentEntity);
    }
}
