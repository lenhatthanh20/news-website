package com.lenhatthanh.blog.infrastructure.repository;

import com.lenhatthanh.blog.infrastructure.repository.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleEntity, String> {
}
