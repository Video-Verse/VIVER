package com.project.viver.service.common.oauth;

import com.project.viver.common.constraint.oauth.OAuthAttributes;

public interface SocialLoginApiService {

	OAuthAttributes getUserInfo(String accessToken);
}
