package com.lenhatthanh.blog.service;

import com.lenhatthanh.blog.dto.UserDto;
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

    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> new UserDto(user.getUserId(), user.getName(), user.getEmail()))
                .toList();
    }

    public UserDto findByEmail(String id) {
        Optional<User> user = userRepository.findOneByEmail(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("The user is not found");
        }

        return new UserDto(user.get().getUserId(), user.get().getName(), user.get().getEmail());
    }
}
