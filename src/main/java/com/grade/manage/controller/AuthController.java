package com.grade.manage.controller;

import com.grade.manage.dto.auth.AuthResponse;
import com.grade.manage.dto.auth.LoginRequest;
import com.grade.manage.dto.create.UserCreateDTO;
import com.grade.manage.dto.simple.UserDTO;
import com.grade.manage.response.ApiResponse;
import com.grade.manage.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Studios TKOH!
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.authenticate(request);
        return ResponseEntity.ok(ApiResponse.ok("Authenticated successfully", response));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> register(@Valid @RequestBody UserCreateDTO request) {
        UserDTO user = authService.register(request);
        return ResponseEntity.ok(ApiResponse.ok("User registered successfully", user));
    }
}
