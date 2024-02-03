package com.lenhatthanh.blog.modules.user.application.usecase;

import com.lenhatthanh.blog.modules.user.domain.service.UpdateUserService;
import com.lenhatthanh.blog.modules.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateSubscriberUserUseCase {
    UpdateUserService updateUserService;
    public void execute(UserDto user) {
        updateUserService.updateSubscriber(user);
    }
}
