package com.lenhatthanh.blog.domain.repository;

import com.lenhatthanh.blog.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepositoryInterface {
    void save(Role role);

    void saveAll(List<Role> roles);

    Optional<Role> findByName(String name);
}
