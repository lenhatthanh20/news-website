package com.lenhatthanh.blog.modules.user.infrastructure.repository.entity;

import com.lenhatthanh.blog.modules.post.infrastructure.repository.entity.PostEntity;
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
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 6009937215357249662L;

    @Id
    @Column(nullable = false, length = 100, unique = true)
    private String id;

    @Version
    @Column(nullable = false)
    private Long version;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 15, unique = true)
    private String mobilePhone;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Boolean isActive;

    /**
     * One to many with `posts` table
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PostEntity> posts = new HashSet<>();

    /**
     * Many to many with `roles` table
     */
    @ElementCollection
    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_id")
    private Set<String> roleIds = new HashSet<>();

    public UserEntity(String id, Long version, String name, String email, String mobilePhone, String password, Boolean isActive) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.password = password;
        this.isActive = isActive;
    }

    public void addRole(String roleId) {
        this.roleIds.add(roleId);
    }

    public void removeRole(String roleId) {
        this.roleIds.remove(roleId);
    }
}
