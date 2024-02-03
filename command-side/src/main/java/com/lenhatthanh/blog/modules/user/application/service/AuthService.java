package com.lenhatthanh.blog.modules.user.application.service;

import com.lenhatthanh.blog.modules.user.application.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.repository.UserRepository;
import com.lenhatthanh.blog.modules.user.dto.LoginDto;
import com.lenhatthanh.blog.modules.user.dto.LoginResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class AuthService {
    private final UserRepository usersRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponseDto login(LoginDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = usersRepository.findByEmail(request.getEmail())
                .orElseThrow(UserNotFoundException::new);
        String token = jwtService.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getEmail().getValue(),
                        user.getPassword(),
                        true,
                        true,
                        true,
                        true,
                        new ArrayList<>()
                )
        );

        return new LoginResponseDto(user.getId().toString(), token);
    }
}
