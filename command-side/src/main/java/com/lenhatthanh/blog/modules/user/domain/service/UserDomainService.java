package com.lenhatthanh.blog.modules.user.domain.service;

import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.dto.UserDto;

public interface UserDomainService {
    User createNewUser(UserDto userDto);

    User deletedUser(User user);

    User deactivateUser(User user);
}
