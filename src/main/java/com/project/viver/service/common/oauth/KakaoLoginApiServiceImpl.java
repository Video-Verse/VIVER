package com.project.viver.service.common.oauth;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.project.viver.api.KakaoUserInfoClient;
import com.project.viver.common.constraint.UserType;
import com.project.viver.common.constraint.jwt.GrantType;
import com.project.viver.common.constraint.oauth.OAuthAttributes;
import com.project.viver.dto.oauth.KakaoUserInfoResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class KakaoLoginApiServiceImpl implements SocialLoginApiService {

    private final KakaoUserInfoClient kakaoUserInfoClient;
    private final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf8";

    @Override
    public OAuthAttributes getUserInfo(String accessToken) {
        KakaoUserInfoResponseDto kakaoUserInfoResponseDto = kakaoUserInfoClient.getKakaoUserInfo(CONTENT_TYPE,
                GrantType.BEARER.getType() + " " + accessToken);
        KakaoUserInfoResponseDto.KakaoAccount kakaoAccount = kakaoUserInfoResponseDto.getKakaoAccount();
        String userId = kakaoAccount.getEmail();

        return OAuthAttributes.builder()
                .userId(!StringUtils.hasText(userId) ? kakaoUserInfoResponseDto.getId() : userId)
                .name(kakaoAccount.getProfile().getNickname())
                .profile(kakaoAccount.getProfile().getThumbnailImageUrl())
                .userType(UserType.KAKAO)
                .build();
    }

}
