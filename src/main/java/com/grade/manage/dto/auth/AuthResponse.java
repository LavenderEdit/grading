package com.grade.manage.dto.auth;

import java.time.Instant;
import lombok.Builder;
import lombok.Value;

/**
 *
 * @author Studios TKOH!
 */
@Value
@Builder
public class AuthResponse {

    String accessToken;
    String tokenType;
    Instant expiresAt;
}
