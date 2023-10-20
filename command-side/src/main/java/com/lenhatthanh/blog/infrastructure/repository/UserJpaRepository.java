package com.lenhatthanh.blog.infrastructure.repository;

import com.lenhatthanh.blog.infrastructure.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, String> {
}
