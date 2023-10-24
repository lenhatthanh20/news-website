package com.lenhatthanh.blog.service;

import com.lenhatthanh.blog.dto.RoleDto;
import com.lenhatthanh.blog.dto.UserForViewDto;
import com.lenhatthanh.blog.exception.UserNotFoundException;
import com.lenhatthanh.blog.model.Role;
import com.lenhatthanh.blog.model.User;
import com.lenhatthanh.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                    UserForViewDto userDto = new UserForViewDto(user.getUserId(), user.getName(), user.getEmail(), new ArrayList<>());
                    List<Role> roles = user.getRoles() == null ? new ArrayList<>() : user.getRoles();
                    for (Role role : roles) {
                        userDto.addRole(new RoleDto(role.getRoleId(), role.getName(), role.getDescription()));
                    }

                    return userDto;
                })
                .toList();
    }

    public UserForViewDto findByEmail(String id) {
        Optional<User> user = userRepository.findOneByEmail(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("The user is not found");
        }

        UserForViewDto userDto = new UserForViewDto(user.get().getUserId(), user.get().getName(), user.get().getEmail(), new ArrayList<>());
        for (Role role : user.get().getRoles()) {
            userDto.addRole(new RoleDto(role.getRoleId(), role.getName(), role.getDescription()));
        }

        return userDto;
    }
}
