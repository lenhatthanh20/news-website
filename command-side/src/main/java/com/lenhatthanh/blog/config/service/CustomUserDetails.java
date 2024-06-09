package com.lenhatthanh.blog.config.service;

import com.lenhatthanh.blog.modules.user.infra.persistence.entity.RoleEntity;
import com.lenhatthanh.blog.modules.user.infra.persistence.entity.UserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    @Getter
    private final String id;
    @Getter
    private final String email;
    @Getter
    private final String name;
    private final Collection<SimpleGrantedAuthority> authorities;
    private final String password;

    public CustomUserDetails(UserEntity userEntity, List<RoleEntity> roles) {
        id = userEntity.getId();
        name = userEntity.getName();
        email = userEntity.getEmail();
        password = userEntity.getPassword();
        authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
