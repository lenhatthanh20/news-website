package com.lenhatthanh.blog.config.service;

import com.lenhatthanh.blog.modules.user.application.externalservice.AuthService;
import com.lenhatthanh.blog.modules.user.dto.LoginDto;
import com.lenhatthanh.blog.modules.user.dto.LoginResponseDto;
import com.lenhatthanh.blog.modules.user.infra.persistence.UserJpaRepository;
import com.lenhatthanh.blog.modules.user.infra.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserJpaRepository usersRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponseDto login(LoginDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserEntity user = usersRepository.findByEmail(request.getEmail())
                .filter(this::isUserActive)
                .orElseThrow(RuntimeException::new);
        String token = jwtService.generateToken(createUserDetails(user));
        return createLoginResponse(user, token);
    }

    private Boolean isUserActive(UserEntity user) {
        return !user.getIsDeleted() && user.getIsActive();
    }

    private UserDetails createUserDetails(UserEntity user) {
        return new CustomUserDetails(user, new ArrayList<>());
    }

    private LoginResponseDto createLoginResponse(UserEntity user, String token) {
        return new LoginResponseDto(user.getId(), token);
    }
}
