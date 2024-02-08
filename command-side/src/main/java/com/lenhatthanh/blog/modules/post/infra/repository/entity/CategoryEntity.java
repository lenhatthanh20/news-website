package com.lenhatthanh.blog.modules.post.infra.repository.entity;

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
@Table(name="categories")
public class CategoryEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 6009937215347249661L;

    @Id
    @Column(nullable = false, unique = true, length = 100)
    private String id;

    @Version
    @Column(nullable = false)
    private Long version;

    @Column()
    private String parentId;

    @Column(nullable = false, length = 100)
    private String slug;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public CategoryEntity(String id, Long version, String title, String slug) {
        this.id = id;
        this.version = version;
        this.title = title;
        this.slug = slug;
    }

    public CategoryEntity(String id, Long version, String parentId, String title, String slug) {
        this.id = id;
        this.version = version;
        this.parentId = parentId;
        this.title = title;
        this.slug = slug;
    }
}
