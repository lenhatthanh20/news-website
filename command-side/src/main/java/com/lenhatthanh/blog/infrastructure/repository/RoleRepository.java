package com.lenhatthanh.blog.infrastructure.repository;

import com.lenhatthanh.blog.domain.Role;
import com.lenhatthanh.blog.domain.repository.RoleRepositoryInterface;
import com.lenhatthanh.blog.infrastructure.repository.entity.RoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class RoleRepository implements RoleRepositoryInterface {
    private RoleJpaRepository roleJpaRepository;

    @Override
    public void save(Role user) {
        RoleEntity roleEntity = new RoleEntity(
                user.getId(),
                user.getName(),
                user.getDescription()
        );

        roleJpaRepository.save(roleEntity);
    }

    @Override
    public Optional<Role> findByName(String name) {
        Optional<RoleEntity> roleEntity = this.roleJpaRepository.findByName(name);
        if (roleEntity.isEmpty()) {
            return Optional.empty();
        }

        Role role = new Role(roleEntity.get().getId(), roleEntity.get().getName(), roleEntity.get().getDescription());

        return Optional.of(role);
    }
}
