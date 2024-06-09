package com.lenhatthanh.blog.modules.user.application.usecase.implement;

import com.lenhatthanh.blog.modules.user.application.evenpublisher.UserEventPublisher;
import com.lenhatthanh.blog.modules.user.application.usecase.UpdateUserUseCase;
import com.lenhatthanh.blog.modules.user.domain.*;
import com.lenhatthanh.blog.modules.user.domain.exception.UserAlreadyExistsException;
import com.lenhatthanh.blog.modules.user.domain.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.user.application.repository.UserRepository;
import com.lenhatthanh.blog.modules.user.domain.service.UserDomainService;
import com.lenhatthanh.blog.modules.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private UserRepository userRepository;
    private UserDomainService userDomainService;
    private UserEventPublisher publisher;

    public void execute(UserDto newUserDto) {
        User user = getUserByIdOrError(newUserDto.getId());
        if (!user.getEmail().getValue().equals(newUserDto.getName())) {
            this.newEmailDoesNotExistOrError(newUserDto.getEmail());
        }

        User updatedUser = this.userDomainService.updateUser(user, newUserDto);
        this.userRepository.save(updatedUser);
        this.publishDomainEvents(updatedUser);
    }

    private User getUserByIdOrError(String userId) {
        return this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
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
