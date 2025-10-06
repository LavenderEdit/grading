package com.grade.manage.service.impl;

import com.grade.manage.dto.auth.AuthResponse;
import com.grade.manage.dto.auth.LoginRequest;
import com.grade.manage.dto.create.UserCreateDTO;
import com.grade.manage.dto.simple.UserDTO;
import com.grade.manage.service.AuthService;
import com.grade.manage.service.UserService;
import com.grade.manage.util.security.JwtTokenProvider;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Studios TKOH!
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    public AuthResponse authenticate(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(authentication);
        Instant expiresAt = tokenProvider.getExpirationInstant(token);
        return AuthResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresAt(expiresAt)
                .build();
    }

    @Override
    public UserDTO register(UserCreateDTO request) {
        return userService.create(request);
    }
}
