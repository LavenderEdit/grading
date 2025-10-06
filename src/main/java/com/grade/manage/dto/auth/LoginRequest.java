package com.grade.manage.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author Studios TKOH!
 */
@Data
public class LoginRequest {
    
    @Email
    @NotBlank
    @Size(max = 60)
    private String email;

    @NotBlank
    @Size(min = 8, max = 72)
    private String password;
}
