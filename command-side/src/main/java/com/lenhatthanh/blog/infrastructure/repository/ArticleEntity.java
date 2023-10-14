package com.lenhatthanh.blog.infrastructure.repository;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

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
    private AuthorEntity author;
}
