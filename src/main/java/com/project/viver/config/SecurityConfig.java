package com.project.viver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.viver.service.common.token.TokenManagerService;

@Configuration
public class SecurityConfig {

    @Value("${token.access-token-expiration-time}")
    private String accessTokenExpirationTime;

    @Value("${token.refresh-token-expiration-time}")
    private String refreshTokenExpirationTime;

    @Value("${token.secret}")
    private String tokenSecret;

    @Bean
    public TokenManagerService tokenManager() {
        return new TokenManagerService(accessTokenExpirationTime, refreshTokenExpirationTime, tokenSecret);
    }

}
