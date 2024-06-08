package com.lenhatthanh.blog.modules.user.application.usecase.implement;

import com.lenhatthanh.blog.modules.user.application.evenpublisher.UserEventPublisher;
import com.lenhatthanh.blog.modules.user.application.usecase.UpdateAuthorUserUseCase;
import com.lenhatthanh.blog.modules.user.domain.*;
import com.lenhatthanh.blog.modules.user.domain.exception.UserAlreadyExistsException;
import com.lenhatthanh.blog.modules.user.domain.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.user.application.repository.UserRepository;
import com.lenhatthanh.blog.modules.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateAuthorUserUseCaseImpl implements UpdateAuthorUserUseCase {
    private UserRepository userRepository;
    private UserEventPublisher publisher;

    public void execute(UserDto newUserDto) {
        User user = getUserByIdOrError(newUserDto.getId());
        if (!user.getEmail().getValue().equals(newUserDto.getName())) {
            this.newEmailDoesNotExistOrError(newUserDto.getEmail());
        }

        user.updateName(new UserName(newUserDto.getName()));
        user.updateEmail(new Email(newUserDto.getEmail()));
        user.updateMobilePhone(new MobilePhone(newUserDto.getMobilePhone()));

        userRepository.save(user);
        this.publishDomainEvents(user);
    }

    private User getUserByIdOrError(String userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    private void newEmailDoesNotExistOrError(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException();
        }
    }

    private void publishDomainEvents(User user) {
        user.getDomainEvents().forEach(event -> publisher.publish(event));
    }
}
