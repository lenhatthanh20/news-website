package com.lenhatthanh.blog.modules.user.domain.service;

import com.lenhatthanh.blog.core.domain.AggregateId;
import com.lenhatthanh.blog.modules.user.domain.*;
import com.lenhatthanh.blog.modules.user.domain.exception.RoleNotFoundException;
import com.lenhatthanh.blog.modules.user.domain.repository.RoleRepositoryInterface;
import com.lenhatthanh.blog.modules.user.domain.repository.UserRepositoryInterface;
import com.lenhatthanh.blog.modules.user.dto.UserDto;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateUserService implements CreateUserServiceInterface {
    private UserRepositoryInterface userRepository;
    private RoleRepositoryInterface roleRepository;
    private PasswordEncoder passwordEncoder;

    public void createSubscriber(UserDto userDto) {
        Role role = this.getRoleByNameOrError(SystemRole.SUBSCRIBER);
        User user = User.create(
                new AggregateId(UniqueIdGenerator.create()),
                new UserName(userDto.getName()),
                new Email(userDto.getEmail()),
                passwordEncoder.encode(userDto.getPassword())
        );

        user.addRole(role.getId());
        userRepository.save(user);
    }

    public void createAuthor(UserDto userDto) {
        Role role = this.getRoleByNameOrError(SystemRole.AUTHOR);
        User user = User.create(
                new AggregateId(UniqueIdGenerator.create()),
                new UserName(userDto.getName()),
                new Email(userDto.getEmail()),
                passwordEncoder.encode(userDto.getPassword())
        );

        user.addRole(role.getId());
        userRepository.save(user);
    }

    public void createAdmin(UserDto userDto) {
        Role role = this.getRoleByNameOrError(SystemRole.ADMIN);
        User user = User.create(
                new AggregateId(UniqueIdGenerator.create()),
                new UserName(userDto.getName()),
                new Email(userDto.getEmail()),
                passwordEncoder.encode(userDto.getPassword())
        );

        user.addRole(role.getId());
        userRepository.save(user);
    }

    private Role getRoleByNameOrError(String roleName) {
        Optional<Role> roleUser = roleRepository.findByName(roleName);
        if (roleUser.isEmpty()) {
            throw new RoleNotFoundException();
        }

        return roleUser.get();
    }
}
