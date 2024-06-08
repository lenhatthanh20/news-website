package com.lenhatthanh.blog.modules.post.infra.persistence.entity;

import com.lenhatthanh.blog.modules.post.domain.Slug;
import com.lenhatthanh.blog.modules.post.domain.Tag;
import com.lenhatthanh.blog.modules.post.domain.Title;
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
@Table(name = "tags")
public class TagEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 6009937215347249661L;

    @Id
    @Column(nullable = false, unique = true, length = 100)
    private String id;

    @Version
    @Column(nullable = false)
    private Long version;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 100)
    private String slug;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public TagEntity(String id, Long version, String title, String slug) {
        this.id = id;
        this.version = version;
        this.title = title;
        this.slug = slug;
    }

    public static TagEntity fromDomainModel(Tag tag) {
        return new TagEntity(tag.getId().toString(), tag.getAggregateVersion(), tag.getTitle().getValue(), tag.getSlug().getValue());
    }

    public static Tag toDomainModel(TagEntity tagEntity) {
        Tag tag = Tag.builder().title(new Title(tagEntity.getTitle())).slug(new Slug(tagEntity.getSlug())).build();
        tag.setId(new com.lenhatthanh.blog.core.domain.Id(tagEntity.getId()));
        tag.setAggregateVersion(tagEntity.getVersion());
        return tag;
    }
}
