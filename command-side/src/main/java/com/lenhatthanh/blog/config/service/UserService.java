package com.lenhatthanh.blog.config.service;

import com.lenhatthanh.blog.modules.user.domain.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.user.domain.entity.User;
import com.lenhatthanh.blog.modules.user.application.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) {
                User userEntity = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
                if(userEntity.getIsDeleted() || !userEntity.getIsActive()) {
                    throw new UserNotFoundException();
                }

                return new org.springframework.security.core.userdetails.User(
                        userEntity.getEmail().getValue(),
                        userEntity.getPassword(),
                        true,
                        true,
                        true,
                        true,
                        new ArrayList<>()
                );
            }
        };
    }
}
