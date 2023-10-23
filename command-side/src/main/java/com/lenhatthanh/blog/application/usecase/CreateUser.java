package com.lenhatthanh.blog.application.usecase;

import com.lenhatthanh.blog.domain.Role;
import com.lenhatthanh.blog.domain.SystemRole;
import com.lenhatthanh.blog.domain.User;
import com.lenhatthanh.blog.domain.repository.RoleRepositoryInterface;
import com.lenhatthanh.blog.domain.repository.UserRepositoryInterface;
import com.lenhatthanh.blog.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CreateUser {
    private UserRepositoryInterface userRepository;
    private RoleRepositoryInterface roleRepository;
    PasswordEncoder passwordEncoder;

    public void execute(UserDto userDto) {
        User user = new User(
                UUID.randomUUID().toString(),
                userDto.getName(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword())
        );

        Optional<Role> roleUser = roleRepository.findByName(SystemRole.SUBSCRIBER);
        roleUser.ifPresent(user::addRole);

        userRepository.save(user);
    }
}
