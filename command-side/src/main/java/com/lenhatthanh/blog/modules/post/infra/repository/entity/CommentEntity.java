package com.lenhatthanh.blog.modules.post.infra.repository.entity;

import com.lenhatthanh.blog.modules.user.infra.repository.entity.UserEntity;
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
@Table(name="comments")
public class CommentEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 6009937215357249661L;

    @Id
    @Column(nullable = false, unique = true, length = 100)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="post_id", nullable=false)
    private PostEntity post;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public CommentEntity(String id, UserEntity user, String content) {
        this.id = id;
        this.user = user;
        this.content = content;
    }
}
