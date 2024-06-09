package com.lenhatthanh.blog.modules.user.application.usecase.implement;

import com.lenhatthanh.blog.modules.user.application.evenpublisher.UserEventPublisher;
import com.lenhatthanh.blog.modules.user.application.usecase.CreateUserUseCase;
import com.lenhatthanh.blog.modules.user.domain.entity.Role;
import com.lenhatthanh.blog.modules.user.domain.entity.User;
import com.lenhatthanh.blog.modules.user.domain.exception.RoleNotFoundException;
import com.lenhatthanh.blog.modules.user.domain.exception.UserAlreadyExistsException;
import com.lenhatthanh.blog.modules.user.application.repository.RoleRepository;
import com.lenhatthanh.blog.modules.user.application.repository.UserRepository;
import com.lenhatthanh.blog.modules.user.domain.service.UserDomainService;
import com.lenhatthanh.blog.modules.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserDomainService userDomainService;
    private PasswordEncoder passwordEncoder;
    private UserEventPublisher publisher;

    public void execute(UserDto userDto) {
        rolesExistOrError(userDto.getRoleIds());
        userDoesNotExistOrError(userDto);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userDomainService.createNewUser(userDto);
        userRepository.save(user);
        publishDomainEvents(user);
    }

    private void userDoesNotExistOrError(UserDto userDto) {
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if (user.isPresent()) {
            throw new UserAlreadyExistsException();
        }
    }

    private void rolesExistOrError(List<String> roleIds) {
        List<Role> roles = roleRepository.findByIds(roleIds);
        if (roles.size() != roleIds.size()) {
            throw new RoleNotFoundException();
        }
    }

    private void publishDomainEvents(User user) {
        user.getDomainEvents().forEach(event -> publisher.publish(event));
    }
}
