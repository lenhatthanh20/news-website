package com.lenhatthanh.blog.modules.user.application.repository;

import com.lenhatthanh.blog.modules.user.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    void save(Role role);

    void saveAll(List<Role> roles);

    Optional<Role> findByName(String name);

    Optional<Role> findById(String id);

    void delete(Role role);
}
