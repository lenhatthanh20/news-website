package com.lenhatthanh.blog.application.service;

import com.lenhatthanh.blog.application.exception.UserNotFoundException;
import com.lenhatthanh.blog.domain.User;
import com.lenhatthanh.blog.domain.repository.UserRepositoryInterface;
import lombok.AllArgsConstructor;
;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepositoryInterface userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) {
                User userEntity = userRepository.findByEmail(email).orElseThrow(()
                        -> new UserNotFoundException("APPLICATION-ERROR-0001")
                );

                return new org.springframework.security.core.userdetails.User(
                        userEntity.getEmail(),
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
