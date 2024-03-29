package com.lenhatthanh.blog.modules.user.application.usecase;

import com.lenhatthanh.blog.modules.user.domain.service.DeleteUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteSubscriberUserUseCase {
    DeleteUserService deleteUserService;

    public void execute(String userId) {
        deleteUserService.deleteSubscriber(userId);
    }
}
