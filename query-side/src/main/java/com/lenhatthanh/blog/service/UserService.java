package com.lenhatthanh.blog.service;

import com.lenhatthanh.blog.dto.RoleDto;
import com.lenhatthanh.blog.dto.UserForViewDto;
import com.lenhatthanh.blog.exception.UserNotFoundException;
import com.lenhatthanh.blog.model.User;
import com.lenhatthanh.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;

    public List<UserForViewDto> getAll() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> {
                    UserForViewDto userDto = new UserForViewDto(user.getUserId(), user.getName(), user.getEmail());
                    user.getRoles().stream().map(role -> new RoleDto(role.getRoleId(), role.getName(), role.getDescription())).forEach(userDto::addRole);

                    return userDto;
                })
                .toList();
    }

    public UserForViewDto findByEmail(String id) {
        Optional<User> user = userRepository.findOneByEmail(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("The user is not found");
        }

        UserForViewDto userDto = new UserForViewDto(user.get().getUserId(), user.get().getName(), user.get().getEmail());
        user.get().getRoles().forEach(role -> userDto.addRole(new RoleDto(role.getRoleId(), role.getName(), role.getDescription())));

        return userDto;
    }
}
