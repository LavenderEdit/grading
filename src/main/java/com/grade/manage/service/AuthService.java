package com.grade.manage.service;

import com.grade.manage.dto.auth.AuthResponse;
import com.grade.manage.dto.auth.LoginRequest;
import com.grade.manage.dto.create.UserCreateDTO;
import com.grade.manage.dto.simple.UserDTO;

/**
 *
 * @author Studios TKOH!
 */
public interface AuthService {

    AuthResponse authenticate(LoginRequest request);

    UserDTO register(UserCreateDTO request);
}
