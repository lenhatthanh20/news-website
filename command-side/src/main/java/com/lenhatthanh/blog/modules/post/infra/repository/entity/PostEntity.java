package com.lenhatthanh.blog.modules.post.infra.repository.entity;

import com.lenhatthanh.blog.modules.user.infra.repository.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name="posts")
public class PostEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 6009937215357249661L;

    @Id
    @Column(nullable = false, unique = true, length = 100)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", length = 20000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @Column(columnDefinition = "TEXT", length = 500)
    private String summary;

    @Column(nullable = false, unique = true)
    private String thumbnail;

    @Column(nullable = false, unique = true, length = 100)
    private String slug;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    @Column(nullable = false, updatable = false)
    private LocalDateTime publishedAt;

    @Version
    @Column(nullable = false)
    private Long version;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public PostEntity(
            String id,
            String title,
            String content,
            UserEntity user,
            String summary,
            String thumbnail,
            String slug,
            LocalDateTime publishedAt,
            Long version
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.summary = summary;
        this.thumbnail = thumbnail;
        this.slug = slug;
        this.publishedAt = publishedAt;
        this.version = version;
    }
}