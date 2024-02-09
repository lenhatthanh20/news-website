package com.lenhatthanh.blog.modules.user.infra.repository.entity;

import com.lenhatthanh.blog.modules.post.infra.repository.entity.CommentEntity;
import com.lenhatthanh.blog.modules.post.infra.repository.entity.PostEntity;
import com.lenhatthanh.blog.modules.user.domain.Email;
import com.lenhatthanh.blog.modules.user.domain.MobilePhone;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.UserName;
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
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<PostEntity> posts = new HashSet<>();

    /**
     * One to many with `comments` table
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<CommentEntity> comments = new HashSet<>();

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

    public static UserEntity fromDomainModel(User user) {
        UserEntity userEntity = new UserEntity(
                user.getId().toString(),
                user.getAggregateVersion(),
                user.getName().getValue(),
                user.getEmail().getValue(),
                user.getMobilePhone().getValue(),
                user.getPassword(),
                user.getIsActive()
        );

        user.getRoleIds().forEach(roleId -> {
            userEntity.addRole(roleId.toString());
        });

        return userEntity;
    }

    public User toDomainModel() {
        var id = new com.lenhatthanh.blog.core.domain.Id(this.id);

        return new User(
                id,
                this.version,
                new UserName(this.name),
                new Email(this.email),
                new MobilePhone(this.mobilePhone),
                this.password,
                this.isActive
        );
    }
}
