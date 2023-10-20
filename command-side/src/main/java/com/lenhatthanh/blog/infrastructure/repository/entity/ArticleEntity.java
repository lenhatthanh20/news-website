package com.lenhatthanh.blog.infrastructure.repository.entity;

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
    @Column(nullable = false, unique = true)
    private String id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    private String summary;

    @Column(nullable = false)
    private String thumbnail;

    @Column(nullable = false, unique = true)
    private String slug;

    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
