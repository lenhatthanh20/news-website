package com.lenhatthanh.blog.domain.repository;

import com.lenhatthanh.blog.domain.Role;

import java.util.Optional;

public interface RoleRepositoryInterface {
    void save(Role role);

    Optional<Role> findByName(String name);
}
