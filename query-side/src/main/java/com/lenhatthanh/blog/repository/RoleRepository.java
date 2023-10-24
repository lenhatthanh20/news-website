package com.lenhatthanh.blog.repository;

import com.lenhatthanh.blog.model.Role;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface RoleRepository extends RedisDocumentRepository<Role, String> {
}
