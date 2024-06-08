package com.lenhatthanh.blog.modules.post.infra.persistence.entity;

import com.lenhatthanh.blog.modules.post.domain.Comment;
import com.lenhatthanh.blog.modules.user.infra.persistence.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "comments")
public class CommentEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 6009937215357249661L;

    @Id
    @Column(nullable = false, unique = true, length = 100)
    private String id;

    @Version
    @Column(nullable = false)
    private Long version;

    @Column()
    private String parentId;

    @Column()
    private String content;

    @Column()
    private boolean isApproved;

    @Column()
    private LocalDateTime publishedAt;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    /**
     * Many to one with `posts` table
     */
    @JoinColumn(name = "post_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = PostEntity.class)
    private PostEntity post;

    @Column(name = "post_id")
    private String postId;

    /**
     * Many to one with `users` table
     */
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = UserEntity.class)
    private UserEntity user;

    @Column(name = "user_id")
    private String userId;

    public CommentEntity(
            String id,
            Long version,
            String content,
            boolean isApproved,
            LocalDateTime publishedAt
    ) {
        this.id = id;
        this.version = version;
        this.content = content;
        this.isApproved = isApproved;
        this.publishedAt = publishedAt;
    }

    public static CommentEntity fromDomainModel(Comment comment) {
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

        return commentEntity;
    }
}
