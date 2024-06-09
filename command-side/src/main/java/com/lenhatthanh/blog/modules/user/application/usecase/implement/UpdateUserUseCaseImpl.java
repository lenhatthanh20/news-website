package com.lenhatthanh.blog.modules.user.application.usecase.implement;

import com.lenhatthanh.blog.modules.user.application.evenpublisher.UserEventPublisher;
import com.lenhatthanh.blog.modules.user.application.repository.RoleRepository;
import com.lenhatthanh.blog.modules.user.application.usecase.UpdateUserUseCase;
import com.lenhatthanh.blog.modules.user.domain.*;
import com.lenhatthanh.blog.modules.user.domain.exception.RoleNotFoundException;
import com.lenhatthanh.blog.modules.user.domain.exception.UserAlreadyExistsException;
import com.lenhatthanh.blog.modules.user.domain.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.user.application.repository.UserRepository;
import com.lenhatthanh.blog.modules.user.domain.service.UserDomainService;
import com.lenhatthanh.blog.modules.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserDomainService userDomainService;
    private UserEventPublisher publisher;

    public void execute(UserDto newUserDto) {
        User currentUser = getUserByIdOrError(newUserDto.getId());
        this.rolesExistOrError(newUserDto.getRoleIds());
        if (!currentUser.getEmail().getValue().equals(newUserDto.getName())) {
            this.newEmailDoesNotExistOrError(newUserDto.getEmail());
        }

        User updatedUser = this.userDomainService.updateUser(currentUser, newUserDto);
        this.userRepository.save(updatedUser);
        this.publishDomainEvents(updatedUser);
    }

    private User getUserByIdOrError(String userId) {
        return this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    private void rolesExistOrError(List<String> roleIds) {
        List<Role> roles = this.roleRepository.findByIds(roleIds);
        if (roles.size() != roleIds.size()) {
            throw new RoleNotFoundException();
        }
    }

    private void newEmailDoesNotExistOrError(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException();
        }
    }

    private void publishDomainEvents(User user) {
        user.getDomainEvents().forEach(event -> this.publisher.publish(event));
    }
}
