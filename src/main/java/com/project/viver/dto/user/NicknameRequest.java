package com.project.viver.dto.user;

import com.project.viver.common.constraint.oauth.OAuthAttributes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NicknameRequest {
    private String nickname;
    private OAuthAttributes oauthAttributes;

}
