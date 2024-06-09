package com.lenhatthanh.blog.modules.user.infra.persistence;

import com.lenhatthanh.blog.modules.user.domain.Role;
import com.lenhatthanh.blog.modules.user.application.repository.RoleRepository;
import com.lenhatthanh.blog.modules.user.infra.persistence.entity.RoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {
    private RoleJpaRepository roleJpaRepository;

    @Override
    public void save(Role role) {
        RoleEntity roleEntity = RoleEntity.fromDomainModel(role);
        roleJpaRepository.save(roleEntity);
    }

    @Override
    public void saveAll(List<Role> roles) {
        List<RoleEntity> roleEntities = RoleEntity.fromDomainModels(roles);
        roleJpaRepository.saveAll(roleEntities);
    }

    @Override
    public Optional<Role> findByName(String name) {
        Optional<RoleEntity> roleEntity = roleJpaRepository.findByName(name);

        return roleEntity.map(RoleEntity::toDomainModel);
    }

    @Override
    public Optional<Role> findById(String id) {
        Optional<RoleEntity> roleEntity = roleJpaRepository.findById(id);

        return roleEntity.map(RoleEntity::toDomainModel);
    }

    public List<Role> findByIds(List<String> ids) {
        List<RoleEntity> roleEntities = roleJpaRepository.findAllById(ids);

        return roleEntities.stream().map(RoleEntity::toDomainModel).toList();
    }

    @Override
    public void delete(Role role) {
        roleJpaRepository.deleteById(role.getId().toString());
    }
}
