package com.lenhatthanh.blog.modules.user.infra.persistence.entity;

import com.lenhatthanh.blog.core.domain.RoleStatus;
import com.lenhatthanh.blog.modules.user.domain.entity.Role;
import com.lenhatthanh.blog.modules.user.domain.valueobject.RoleDescription;
import com.lenhatthanh.blog.modules.user.domain.valueobject.RoleName;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @Version
    @Column(nullable = false)
    private Long version;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT", length = 1000)
    private String description;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public RoleEntity(String id, Long version, String name, String description, Boolean isDeleted) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.description = description;
        this.isDeleted = isDeleted;
    }

    public static RoleEntity fromDomainModel(Role role) {
        return new RoleEntity(
                role.getId().toString(),
                role.getAggregateVersion(),
                role.getName().getValue(),
                role.getDescription().getValue(),
                role.getStatus() == RoleStatus.DELETED
        );
    }

    public static List<RoleEntity> fromDomainModels(List<Role> roles) {
        return roles.stream().map(RoleEntity::fromDomainModel).collect(Collectors.toList());
    }

    public static Role toDomainModel(RoleEntity roleEntity) {
        Role role = Role.builder()
                .name(new RoleName(roleEntity.getName()))
                .description(new RoleDescription(roleEntity.getDescription()))
                .status(roleEntity.getIsDeleted() ? RoleStatus.DELETED : RoleStatus.ACTIVE)
                .build();
        role.setId(new com.lenhatthanh.blog.core.domain.Id(roleEntity.getId()));
        role.setAggregateVersion(roleEntity.getVersion());
        return role;
    }
}
