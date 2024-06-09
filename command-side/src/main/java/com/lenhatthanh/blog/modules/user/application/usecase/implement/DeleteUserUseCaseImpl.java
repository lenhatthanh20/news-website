package com.lenhatthanh.blog.modules.user.application.usecase.implement;

import com.lenhatthanh.blog.modules.user.application.evenpublisher.UserEventPublisher;
import com.lenhatthanh.blog.modules.user.application.usecase.DeleteAuthorUserUseCase;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.user.application.repository.UserRepository;
import com.lenhatthanh.blog.modules.user.domain.service.UserDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteAuthorUserUseCase {
    UserRepository userRepository;
    UserDomainService userDomainService;
    private UserEventPublisher publisher;

    public void execute(String userId) {
        User user = getUserByIdOrError(userId);
        User deletedUser = userDomainService.deletedUser(user);

        userRepository.save(deletedUser);
        publishDomainEvents(deletedUser);
    }

    private User getUserByIdOrError(String roleId) {
        return userRepository.findById(roleId).orElseThrow(UserNotFoundException::new);
    }

    private void publishDomainEvents(User user) {
        user.getDomainEvents().forEach(event -> publisher.publish(event));
    }
}
