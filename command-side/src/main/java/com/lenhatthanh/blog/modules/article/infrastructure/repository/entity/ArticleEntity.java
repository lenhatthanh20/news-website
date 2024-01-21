package com.lenhatthanh.blog.modules.article.infrastructure.repository.entity;

import com.lenhatthanh.blog.modules.user.infrastructure.repository.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="articles")
public class ArticleEntity implements Serializable {
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

    @Column(columnDefinition = "TEXT", length = 1000)
    private String summary;

    @Column(nullable = false, unique = true)
    private String thumbnail;

    @Column(nullable = false, unique = true, length = 100)
    private String slug;

    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
