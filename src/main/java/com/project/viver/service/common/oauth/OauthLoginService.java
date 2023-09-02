package com.project.viver.service.common.oauth;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.viver.common.constraint.Role;
import com.project.viver.common.constraint.UserType;
import com.project.viver.common.constraint.oauth.OAuthAttributes;
import com.project.viver.dto.oauth.OauthLoginDto;
import com.project.viver.dto.token.JwtTokenDto;
import com.project.viver.entity.user.User;
import com.project.viver.service.common.token.TokenManagerService;
import com.project.viver.service.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OauthLoginService {

    private final UserService userService;
    private final TokenManagerService tokenManager;

    public OauthLoginDto.Response oauthLogin(String accessToken, UserType userType) {
        SocialLoginApiService socialLoginApiService = SocialLoginApiServiceFactory.getSocialLoginApiService(userType);
        OAuthAttributes userInfo = socialLoginApiService.getUserInfo(accessToken);
        log.info("userInfo : {}",  userInfo);

        JwtTokenDto jwtTokenDto;
        boolean isNewUser = false;
        Optional<User> optionalUser = userService.findUserByUserId(userInfo.getUserId());
        if(optionalUser.isEmpty()) { // 신규 회원 가입
            User oauthUser = userInfo.toUserEntity(userType, Role.ADMIN);
            oauthUser = userService.registerUser(oauthUser);
            isNewUser = true;

            // 토큰 생성
            jwtTokenDto = tokenManager.createJwtTokenDto(oauthUser.getUserId(), oauthUser.getRole());
            oauthUser.updateRefreshToken(jwtTokenDto);
        } else { // oauthUser 회원
            User oauthUser = optionalUser.get();

            // 토큰 생성
            jwtTokenDto = tokenManager.createJwtTokenDto(oauthUser.getUserId(), oauthUser.getRole());
            oauthUser.updateRefreshToken(jwtTokenDto);
        }

        return OauthLoginDto.Response.of(jwtTokenDto, isNewUser);
    }

}