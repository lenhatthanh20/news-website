package com.lenhatthanh.blog.modules.post.infra.repository.entity;

import com.lenhatthanh.blog.modules.user.infra.repository.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @Version
    @Column(nullable = false)
    private Long version;

    @Column()
    private String parentId;

    @Column(nullable = false)
    private String title;

    private String metaTitle;

    @Column(columnDefinition = "TEXT", length = 20000)
    private String content;

    @Column(columnDefinition = "TEXT", length = 500)
    private String summary;

    @Column(nullable = false, unique = true)
    private String thumbnail;

    @Column(nullable = false, unique = true, length = 100)
    private String slug;

    @Column(nullable = false, updatable = false)
    private LocalDateTime publishedAt;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // In microservice system, we don't need to join to `users` table
    // We just need to store `user_id` to identify the user who created the post
    // We will verify the user_id with user service in application level
    /**
     * Many to one with `users` table
     */
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = UserEntity.class)
    private UserEntity user;

    @Column(name = "user_id")
    private String userId;

    /**
     * One to many with `comments` table
     */
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private Set<CommentEntity> comments = new HashSet<>();

    /**
     * Many to many with `categories` table
     */
    @ElementCollection
    @CollectionTable(name = "posts_categories", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "category_id")
    private Set<String> categoryIds = new HashSet<>();

    /**
     * Many to many with `tags` table
     */
    @ElementCollection
    @CollectionTable(name = "posts_tags", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "tag_id")
    private Set<String> tagIds = new HashSet<>();

    public PostEntity(
            String id,
            Long version,
            String title,
            String metaTitle,
            String content,
            String summary,
            String thumbnail,
            String slug,
            LocalDateTime publishedAt
    ) {
        this.id = id;
        this.version = version;
        this.title = title;
        this.metaTitle = metaTitle;
        this.content = content;
        this.summary = summary;
        this.thumbnail = thumbnail;
        this.slug = slug;
        this.publishedAt = publishedAt;
    }
}
