package com.lenhatthanh.blog.modules.user.infrastructure.repository;

import com.lenhatthanh.blog.core.domain.AggregateId;
import com.lenhatthanh.blog.core.domain.DomainEventsPublisher;
import com.lenhatthanh.blog.modules.user.domain.Role;
import com.lenhatthanh.blog.modules.user.domain.repository.RoleRepositoryInterface;
import com.lenhatthanh.blog.modules.user.infrastructure.repository.entity.RoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RoleRepository implements RoleRepositoryInterface {
    private RoleJpaRepository roleJpaRepository;
    private DomainEventsPublisher domainEventsPublisher;

    @Override
    public void save(Role role) {
        RoleEntity roleEntity = new RoleEntity(
                role.getId().toString(),
                role.getName(),
                role.getDescription()
        );

        role.publishEvents(domainEventsPublisher);

        roleJpaRepository.save(roleEntity);
    }

    @Override
    public void saveAll(List<Role> roles) {
        Iterable<RoleEntity> roleEntities = roles.stream().map(role -> new RoleEntity(
                role.getId().toString(),
                role.getName(),
                role.getDescription()
        )).toList();

        roles.forEach(role -> role.publishEvents(domainEventsPublisher));

        roleJpaRepository.saveAll(roleEntities);
    }

    @Override
    public Optional<Role> findByName(String name) {
        Optional<RoleEntity> roleEntity = this.roleJpaRepository.findByName(name);
        if (roleEntity.isEmpty()) {
            return Optional.empty();
        }

        Role role = Role.create(
                new AggregateId(roleEntity.get().getId()),
                roleEntity.get().getName(),
                roleEntity.get().getDescription()
        );

        return Optional.of(role);
    }

    @Override
    public Optional<Role> findById(String id) {
        Optional<RoleEntity> roleEntity = this.roleJpaRepository.findById(id);
        if (roleEntity.isEmpty()) {
            return Optional.empty();
        }

        Role role = Role.create(
                new AggregateId(roleEntity.get().getId()),
                roleEntity.get().getName(),
                roleEntity.get().getDescription()
        );

        return Optional.of(role);
    }
}
