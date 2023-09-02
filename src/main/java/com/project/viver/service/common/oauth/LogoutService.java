package com.project.viver.service.common.oauth;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.viver.common.constraint.jwt.TokenType;
import com.project.viver.entity.user.User;
import com.project.viver.error.ErrorCode;
import com.project.viver.error.exception.AuthenticationException;
import com.project.viver.service.common.token.TokenManagerService;
import com.project.viver.service.user.UserService;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LogoutService {

    private final UserService userService;
    private final TokenManagerService tokenManagerService;

    public void logout(String accessToken) {

        // 1. 토큰 검증
    	tokenManagerService.validateToken(accessToken);

        // 2. 토큰 타입 확인
        Claims tokenClaims = tokenManagerService.getTokenClaims(accessToken);
        String tokenType = tokenClaims.getSubject();
        if(!TokenType.isAccessToken(tokenType)) {
            throw new AuthenticationException(ErrorCode.NOT_ACCESS_TOKEN_TYPE);
        }

        // 3. refresh token 만료 처리
        String userId = (String) tokenClaims.get("userId");
        User user = userService.findUserByUser(userId);
        user.expireRefreshToken(LocalDateTime.now());
    }

}