package com.lenhatthanh.blog.modules.user.domain.service;

import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.user.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserServiceImpl implements DeleteUserService {
    UserRepository userRepository;

    @Override
    public void deleteSubscriber(String userId) {
        User user = this.getUserByIdOrError(userId);
        user.delete();
        // TODO: add business logic: Can't delete admin user
        // TODO: register domain event: UserDeletedEvent

        userRepository.delete(user);
    }

    private User getUserByIdOrError(String roleId) {
        return userRepository.findById(roleId).orElseThrow(UserNotFoundException::new);
    }
}
