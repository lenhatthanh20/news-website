package com.lenhatthanh.blog.config.service;

import com.lenhatthanh.blog.modules.user.domain.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.user.infra.persistence.RoleJpaRepository;
import com.lenhatthanh.blog.modules.user.infra.persistence.UserJpaRepository;
import com.lenhatthanh.blog.modules.user.infra.persistence.entity.RoleEntity;
import com.lenhatthanh.blog.modules.user.infra.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    // Directly inject to call details of infrastructure layer
    // Don't need to interact with domain layer
    @Autowired
    private UserJpaRepository userRepository;
    @Autowired
    private RoleJpaRepository roleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        if (userEntity.getIsDeleted() || !userEntity.getIsActive()) {
            throw new UserNotFoundException();
        }

        List<RoleEntity> roles = roleRepository.findAllById(userEntity.getRoleIds());
        return new CustomUserDetails(userEntity, roles);
    }
}
