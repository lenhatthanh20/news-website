package com.lenhatthanh.blog.modules.user.application.usecase;

import com.lenhatthanh.blog.modules.user.dto.UserDto;

public interface UpdateAuthorUserUseCase {
    public void execute(UserDto newUserDto);
}
