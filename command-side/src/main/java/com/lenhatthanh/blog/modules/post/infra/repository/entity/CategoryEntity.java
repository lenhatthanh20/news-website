package com.lenhatthanh.blog.modules.post.infra.repository.entity;;

import com.lenhatthanh.blog.modules.post.domain.Category;
import com.lenhatthanh.blog.modules.post.domain.Slug;
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

    public Category toDomainModel() {
        var id = new com.lenhatthanh.blog.core.domain.Id(this.id);
        var parentId = this.parentId != null ? new com.lenhatthanh.blog.core.domain.Id(this.parentId) : null;

        return new Category(
                id,
                this.version,
                parentId,
                new Title(this.title),
                new Slug(this.slug, new Title(this.title))
        );
    }

    public static CategoryEntity fromDomainModel(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity(
                category.getId().toString(),
                category.getAggregateVersion(),
                category.getTitle().getValue(),
                category.getSlug().getValue()
        );

        if (category.getParentId() != null) {
            categoryEntity.setParentId(category.getParentId().toString());
        }

        return categoryEntity;
    }
}
