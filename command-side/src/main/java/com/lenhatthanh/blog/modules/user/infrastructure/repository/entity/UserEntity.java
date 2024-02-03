package com.lenhatthanh.blog.modules.user.infrastructure.repository.entity;

import com.lenhatthanh.blog.modules.article.infrastructure.repository.entity.ArticleEntity;
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
@Table(name = "users")
public class UserEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 6009937215357249662L;

    @Id
    @Column(nullable = false, length = 100, unique = true)
    private String id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleEntity> articles = new ArrayList<>();

    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = RoleEntity.class, fetch = FetchType.LAZY)
    private RoleEntity role;

    @Column(name = "role_id", nullable = false)
    private String roleId;

    @Version
    @Column(nullable = false)
    private Long version;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public UserEntity(String id, String name, String email, String password, Long version) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.version = version;
    }
}
