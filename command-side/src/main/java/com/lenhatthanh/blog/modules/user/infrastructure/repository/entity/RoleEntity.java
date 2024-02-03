package com.lenhatthanh.blog.modules.user.infrastructure.repository.entity;

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
@Table(name = "roles")
public class RoleEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 6009937215357249663L;

    @Id
    @Column(nullable = false, unique = true, length = 100)
    private String id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT", length = 1000)
    private String description;

    @Version
    @Column(nullable = false)
    private Long version;

    /**
     * One to many with `users` table
     */
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<UserEntity> users = new HashSet<>();

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public RoleEntity(String id, String name, String description, Long version) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.version = version;
    }
}
