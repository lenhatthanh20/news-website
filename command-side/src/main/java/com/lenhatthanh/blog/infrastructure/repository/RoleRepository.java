package com.lenhatthanh.blog.infrastructure.repository;

import com.lenhatthanh.blog.domain.Role;
import com.lenhatthanh.blog.domain.repository.RoleRepositoryInterface;
import com.lenhatthanh.blog.infrastructure.repository.entity.RoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoleRepository implements RoleRepositoryInterface {
    private RoleJpaRepository roleJpaRepository;

    @Override
    public void save(Role author) {
        RoleEntity roleEntity = new RoleEntity(
                author.getId(),
                author.getName(),
                author.getDescription()
        );

        roleJpaRepository.save(roleEntity);
    }
}
