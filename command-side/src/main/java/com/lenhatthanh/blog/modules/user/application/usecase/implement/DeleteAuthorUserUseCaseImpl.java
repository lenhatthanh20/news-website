package com.lenhatthanh.blog.modules.user.application.usecase.implement;

import com.lenhatthanh.blog.modules.user.application.evenpublisher.UserEventPublisher;
import com.lenhatthanh.blog.modules.user.application.usecase.DeleteAuthorUserUseCase;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.user.application.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAuthorUserUseCaseImpl implements DeleteAuthorUserUseCase {
    UserRepository userRepository;
    private UserEventPublisher publisher;

    public void execute(String userId) {
        User user = this.getUserByIdOrError(userId);
        user.delete();
        // TODO: add business logic: Can't delete admin user

        userRepository.delete(user);
        this.publishDomainEvents(user);
    }

    private User getUserByIdOrError(String roleId) {
        return userRepository.findById(roleId).orElseThrow(UserNotFoundException::new);
    }

    private void publishDomainEvents(User user) {
        user.getDomainEvents().forEach(event -> publisher.publish(event));
    }
}
