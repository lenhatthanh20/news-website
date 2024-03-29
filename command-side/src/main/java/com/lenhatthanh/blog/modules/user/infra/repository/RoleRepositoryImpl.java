package com.lenhatthanh.blog.modules.user.infra.repository;

import com.lenhatthanh.blog.core.domain.DomainEventPublisher;
import com.lenhatthanh.blog.modules.user.domain.Role;
import com.lenhatthanh.blog.modules.user.domain.repository.RoleRepository;
import com.lenhatthanh.blog.modules.user.infra.repository.entity.RoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@AllArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {
    private RoleJpaRepository roleJpaRepository;
    private DomainEventPublisher domainEventPublisher;

    @Override
    public void save(Role role) {
        RoleEntity roleEntity = RoleEntity.fromDomainModel(role);
        roleJpaRepository.save(roleEntity);
        role.publishEvents(domainEventPublisher);
    }

    @Override
    public void saveAll(Set<Role> roles) {
        Set<RoleEntity> roleEntities = RoleEntity.fromDomainModels(roles);
        roleJpaRepository.saveAll(roleEntities);
        roles.forEach(role -> role.publishEvents(domainEventPublisher));
    }

    @Override
    public Optional<Role> findByName(String name) {
        Optional<RoleEntity> roleEntity = this.roleJpaRepository.findByName(name);

        return roleEntity.map(RoleEntity::toDomainModel);
    }

    @Override
    public Optional<Role> findById(String id) {
        Optional<RoleEntity> roleEntity = this.roleJpaRepository.findById(id);

        return roleEntity.map(RoleEntity::toDomainModel);
    }

    @Override
    public void delete(Role role) {
        roleJpaRepository.deleteById(role.getId().toString());
        role.publishEvents(domainEventPublisher);
    }
}
