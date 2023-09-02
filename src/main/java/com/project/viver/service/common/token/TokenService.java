package com.project.viver.service.common.token;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.viver.common.constraint.jwt.GrantType;
import com.project.viver.dto.token.AccessTokenResponseDto;
import com.project.viver.entity.user.User;
import com.project.viver.service.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TokenService {

    private final UserService userService;
    private final TokenManagerService tokenManagerService;

    public AccessTokenResponseDto createAccessTokenByRefreshToken(String refreshToken) {
        User user = userService.findUserByRefreshToken(refreshToken);

        Date accessTokenExpireTime = tokenManagerService.createAccessTokenExpireTime();
        String accessToken = tokenManagerService.createAccessToken(user.getUserId(), user.getRole(), accessTokenExpireTime);

        return AccessTokenResponseDto.builder()
                .grantType(GrantType.BEARER.getType())
                .accessToken(accessToken)
                .accessTokenExpireTime(accessTokenExpireTime)
                .build();
    }

}