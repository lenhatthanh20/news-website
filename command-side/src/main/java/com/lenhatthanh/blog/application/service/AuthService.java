package com.lenhatthanh.blog.application.service;

import com.lenhatthanh.blog.application.exception.UserNotFoundException;
import com.lenhatthanh.blog.domain.User;
import com.lenhatthanh.blog.domain.repository.UserRepositoryInterface;
import com.lenhatthanh.blog.dto.LoginDto;
import com.lenhatthanh.blog.dto.LoginResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class AuthService {
    private final UserRepositoryInterface usersRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponseDto login(LoginDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = usersRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("APPLICATION-ERROR-0001"));
        String token = jwtService.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        true,
                        true,
                        true,
                        true,
                        new ArrayList<>()
                )
        );

        return new LoginResponseDto(user.getId(), token);
    }
}
