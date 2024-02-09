package com.lenhatthanh.blog.modules.user.domain.repository;

import com.lenhatthanh.blog.modules.user.domain.Role;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository {
    void save(Role role);

    void saveAll(Set<Role> roles);

    Optional<Role> findByName(String name);

    Optional<Role> findById(String id);

    void delete(Role role);
}
