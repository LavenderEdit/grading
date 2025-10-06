package com.grade.manage.util.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author Studios TKOH!
 */
@ConfigurationProperties(prefix = "app.security.jwt")
public record JwtProperties(
        String secret,
        long expirationMinutes,
        String issuer) {

}
