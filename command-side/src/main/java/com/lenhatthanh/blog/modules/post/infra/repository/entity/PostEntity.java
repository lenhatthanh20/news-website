package com.lenhatthanh.blog.modules.post.infra.repository.entity;

import com.lenhatthanh.blog.modules.post.domain.*;
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

    public static PostEntity fromDomainModel(Post post) {
        PostEntity postEntity = new PostEntity(
                post.getId().toString(),
                post.getAggregateVersion(),
                post.getTitle().getValue(),
                post.getMetaTitle(),
                post.getContent().getValue(),
                post.getSummary().getValue(),
                post.getThumbnail(),
                post.getSlug().getValue(),
                post.getPublishedAt()
        );

        postEntity.setUserId(post.getUserId().toString());

        Set<String> categoryIds = new HashSet<>();
        post.getCategoryIds().forEach(categoryId -> categoryIds.add(categoryId.toString()));
        postEntity.setCategoryIds(categoryIds);

        Set<String> tagIds = new HashSet<>();
        post.getTagIds().forEach(tagId -> tagIds.add(tagId.toString()));
        postEntity.setTagIds(tagIds);

        return postEntity;
    }

    public Post toDomainModel() {
        var id = new com.lenhatthanh.blog.core.domain.Id(this.id);
        var parentId = this.parentId != null ? new com.lenhatthanh.blog.core.domain.Id(this.parentId) : null;
        var userId = new com.lenhatthanh.blog.core.domain.Id(this.userId);

        return new Post(
                id,
                this.version,
                parentId,
                new Title(this.title),
                this.metaTitle,
                new PostContent(this.content),
                userId,
                new Summary(this.summary),
                this.thumbnail,
                new Slug(this.slug, new Title(this.title)),
                this.publishedAt
        );
    }
}
