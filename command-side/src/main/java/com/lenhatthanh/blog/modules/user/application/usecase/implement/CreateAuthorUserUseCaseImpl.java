package com.lenhatthanh.blog.modules.user.application.usecase.implement;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.user.application.usecase.CreateAuthorUserUseCase;
import com.lenhatthanh.blog.modules.user.domain.Role;
import com.lenhatthanh.blog.modules.user.domain.SystemRole;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.exception.RoleNotFoundException;
import com.lenhatthanh.blog.modules.user.domain.exception.UserAlreadyExistsException;
import com.lenhatthanh.blog.modules.user.domain.repository.RoleRepository;
import com.lenhatthanh.blog.modules.user.domain.repository.UserRepository;
import com.lenhatthanh.blog.modules.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateAuthorUserUseCaseImpl implements CreateAuthorUserUseCase {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public void execute(UserDto userDto) {
        Role role = this.getRoleByNameOrError();
        User user = createUserWithEmailDoesNotExistBeforeOrError(userDto, role.getId());
        userRepository.save(user);
    }

    private User createUserWithEmailDoesNotExistBeforeOrError(UserDto userDto, Id roleId) {
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if (user.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return User.create(userDto, roleId);
    }

    private Role getRoleByNameOrError() {
        Optional<Role> roleUser = roleRepository.findByName(SystemRole.AUTHOR);
        if (roleUser.isEmpty()) {
            throw new RoleNotFoundException();
        }

        return roleUser.get();
    }
}
