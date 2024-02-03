package com.lenhatthanh.blog.modules.user.domain.service;

import com.lenhatthanh.blog.modules.user.dto.UserDto;

public interface CreateUserService {
    void createSubscriber(UserDto userDto);
    void createAdmin(UserDto userDto);
    void createAuthor(UserDto userDto);
}
