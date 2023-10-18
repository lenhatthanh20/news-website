package com.lenhatthanh.blog.infrastructure.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="authors")
public class AuthorEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 6009937215357249662L;

    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleEntity> articles = new ArrayList<>();
}
