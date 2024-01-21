package com.lenhatthanh.blog.modules.user.application.usecase;

import com.lenhatthanh.blog.core.domain.AggregateId;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import com.lenhatthanh.blog.modules.user.domain.Role;
import com.lenhatthanh.blog.modules.user.domain.SystemRole;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.dto.UserDto;
import com.lenhatthanh.blog.modules.user.application.exception.RoleNotFoundException;
import com.lenhatthanh.blog.modules.user.domain.repository.RoleRepositoryInterface;
import com.lenhatthanh.blog.modules.user.domain.repository.UserRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateUser {
    private UserRepositoryInterface userRepository;
    private RoleRepositoryInterface roleRepository;
    PasswordEncoder passwordEncoder;

    public void execute(UserDto userDto) {
        User user = User.create(
                new AggregateId(UniqueIdGenerator.create()),
                userDto.getName(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword())
        );

        // Default role for user is: SUBSCRIBER
        Role role = this.getSubscriberRoleOrError();
        user.addRole(role.getId());

        userRepository.save(user);
    }

    private Role getSubscriberRoleOrError() {
        Optional<Role> roleUser = roleRepository.findByName(SystemRole.SUBSCRIBER);
        if (roleUser.isEmpty()) {
            throw new RoleNotFoundException();
        }

        return roleUser.get();
    }
}
