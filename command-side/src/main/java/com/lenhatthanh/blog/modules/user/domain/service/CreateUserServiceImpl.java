package com.lenhatthanh.blog.modules.user.domain.service;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.user.domain.*;
import com.lenhatthanh.blog.modules.user.domain.exception.RoleNotFoundException;
import com.lenhatthanh.blog.modules.user.domain.exception.UserAlreadyExistsException;
import com.lenhatthanh.blog.modules.user.domain.repository.RoleRepository;
import com.lenhatthanh.blog.modules.user.domain.repository.UserRepository;
import com.lenhatthanh.blog.modules.user.dto.UserDto;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateUserServiceImpl implements CreateUserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public void createSubscriber(UserDto userDto) {
        Role role = this.getRoleByNameOrError(SystemRole.SUBSCRIBER);
        User user = createUserDoesNotExistBeforeOrError(userDto);
        user.addRole(role.getId());
        userRepository.save(user);
    }

    public void createAuthor(UserDto userDto) {
        Role role = this.getRoleByNameOrError(SystemRole.AUTHOR);
        User user = createUserDoesNotExistBeforeOrError(userDto);
        user.addRole(role.getId());
        userRepository.save(user);
    }

    public void createAdmin(UserDto userDto) {
        Role role = this.getRoleByNameOrError(SystemRole.ADMIN);
        User user = createUserDoesNotExistBeforeOrError(userDto);
        user.addRole(role.getId());
        userRepository.save(user);
    }

    private User createUserDoesNotExistBeforeOrError(UserDto userDto) {
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if (user.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        return User.create(
                new Id(UniqueIdGenerator.create()),
                new UserName(userDto.getName()),
                new Email(userDto.getEmail()),
                passwordEncoder.encode(userDto.getPassword())
        );
    }

    private Role getRoleByNameOrError(String roleName) {
        Optional<Role> roleUser = roleRepository.findByName(roleName);
        if (roleUser.isEmpty()) {
            throw new RoleNotFoundException();
        }

        return roleUser.get();
    }
}
