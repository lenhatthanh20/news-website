package com.lenhatthanh.blog.modules.post.infra.persistence.entity;

import com.lenhatthanh.blog.modules.post.domain.*;
import com.lenhatthanh.blog.modules.user.infra.persistence.entity.UserEntity;
import com.lenhatthanh.blog.modules.user.domain.valueobject.PostStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "posts")
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

    @Column()
    private LocalDateTime publishedAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PostStatus status;

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
    private List<CommentEntity> comments = new ArrayList<>();

    /**
     * Many to many with `categories` table
     */
    @ElementCollection
    @CollectionTable(name = "posts_categories", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "category_id")
    private List<String> categoryIds = new ArrayList<>();

    /**
     * Many to many with `tags` table
     */
    @ElementCollection
    @CollectionTable(name = "posts_tags", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "tag_id")
    private List<String> tagIds = new ArrayList<>();

    public PostEntity(
            String id,
            Long version,
            String title,
            String metaTitle,
            String content,
            String summary,
            String thumbnail,
            String slug,
            LocalDateTime publishedAt,
            PostStatus status
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
        this.status = status;
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
                post.getPublishedAt(),
                post.getStatus()
        );

        postEntity.setUserId(post.getUserId().toString());

        List<String> categoryIds = new ArrayList<>();
        post.getCategoryIds().forEach(categoryId -> categoryIds.add(categoryId.toString()));
        postEntity.setCategoryIds(categoryIds);

        List<String> tagIds = new ArrayList<>();
        post.getTagIds().forEach(tagId -> tagIds.add(tagId.toString()));
        postEntity.setTagIds(tagIds);

        return postEntity;
    }

    public static Post toDomainModel(PostEntity entity) {
        Post post = Post.builder()
                .parentId(entity.getParentId() != null ? new com.lenhatthanh.blog.core.domain.Id(entity.getParentId()) : null)
                .userId(new com.lenhatthanh.blog.core.domain.Id(entity.getUserId()))
                .title(new Title(entity.getTitle()))
                .metaTitle(entity.getMetaTitle())
                .content(new PostContent(entity.getContent()))
                .summary(new Summary(entity.getSummary()))
                .thumbnail(entity.getThumbnail())
                .slug(new Slug(entity.getSlug()))
                .publishedAt(entity.getPublishedAt())
                .status(entity.getStatus())
                .build();

        post.setId(new com.lenhatthanh.blog.core.domain.Id(entity.getUserId()));
        post.setAggregateVersion(entity.getVersion());
        post.setCategoryIds(entity.getCategoryIds().stream().map(com.lenhatthanh.blog.core.domain.Id::new).toList());
        post.setTagIds(entity.getTagIds().stream().map(com.lenhatthanh.blog.core.domain.Id::new).toList());

        return post;
    }
}
