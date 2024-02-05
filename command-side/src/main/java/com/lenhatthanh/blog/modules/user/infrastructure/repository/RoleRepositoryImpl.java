package com.lenhatthanh.blog.modules.user.infrastructure.repository;

import com.lenhatthanh.blog.core.domain.DomainEventPublisher;
import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.user.domain.Role;
import com.lenhatthanh.blog.modules.user.domain.RoleDescription;
import com.lenhatthanh.blog.modules.user.domain.RoleName;
import com.lenhatthanh.blog.modules.user.domain.repository.RoleRepository;
import com.lenhatthanh.blog.modules.user.infrastructure.repository.entity.RoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {
    private RoleJpaRepository roleJpaRepository;
    private DomainEventPublisher domainEventPublisher;

    @Override
    public void save(Role role) {
        RoleEntity roleEntity = new RoleEntity(
                role.getId().toString(),
                role.getAggregateVersion(),
                role.getName().getValue(),
                role.getDescription().getValue()
        );

        roleJpaRepository.save(roleEntity);
        role.publishEvents(domainEventPublisher);
    }

    @Override
    public void saveAll(List<Role> roles) {
        Iterable<RoleEntity> roleEntities = roles.stream().map(role -> new RoleEntity(
                role.getId().toString(),
                role.getAggregateVersion(),
                role.getName().getValue(),
                role.getDescription().getValue()
        )).toList();

        roleJpaRepository.saveAll(roleEntities);
        roles.forEach(role -> role.publishEvents(domainEventPublisher));
    }

    @Override
    public Optional<Role> findByName(String name) {
        Optional<RoleEntity> roleEntity = this.roleJpaRepository.findByName(name);
        if (roleEntity.isEmpty()) {
            return Optional.empty();
        }

        Role role = new Role(
                new Id(roleEntity.get().getId()),
                roleEntity.get().getVersion(),
                new RoleName(roleEntity.get().getName()),
                new RoleDescription(roleEntity.get().getDescription())
        );

        return Optional.of(role);
    }

    @Override
    public Optional<Role> findById(String id) {
        Optional<RoleEntity> roleEntity = this.roleJpaRepository.findById(id);
        if (roleEntity.isEmpty()) {
            return Optional.empty();
        }

        Role role = new Role(
                new Id(roleEntity.get().getId()),
                roleEntity.get().getVersion(),
                new RoleName(roleEntity.get().getName()),
                new RoleDescription(roleEntity.get().getDescription())
        );

        return Optional.of(role);
    }

    @Override
    public void delete(Role role) {
        roleJpaRepository.deleteById(role.getId().toString());
        role.publishEvents(domainEventPublisher);
    }
}
