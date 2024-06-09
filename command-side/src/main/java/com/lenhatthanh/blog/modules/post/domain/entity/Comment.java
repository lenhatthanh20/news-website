package com.lenhatthanh.blog.modules.post.domain.entity;

import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.post.dto.CommentDto;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Comment extends AggregateRoot<Id> {
    private Id parentId;
    private String content;
    private boolean isApproved;
    private LocalDateTime publishedAt;

    private Id userId;
    private Id postId;

    public static Comment create(CommentDto commentDto) {
        Comment comment = Comment.builder()
                .parentId(commentDto.getParentId() != null ? new Id(commentDto.getParentId()) : null)
                .content(commentDto.getContent())
                .isApproved(false)
                .publishedAt(LocalDateTime.now())
                .userId(new Id(commentDto.getUserId()))
                .postId(new Id(commentDto.getPostId()))
                .build();
        comment.setId(new Id(UniqueIdGenerator.create()));
        comment.setAggregateVersion(CONCURRENCY_CHECKING_INITIAL_VERSION);
        return comment;
    }
}
