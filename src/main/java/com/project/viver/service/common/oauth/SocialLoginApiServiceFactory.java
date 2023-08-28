package com.project.viver.service.common.oauth;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.viver.common.constraint.UserType;

@Service
public class SocialLoginApiServiceFactory {

    private static Map<String, SocialLoginApiService> socialLoginApiServices;

    public SocialLoginApiServiceFactory(Map<String, SocialLoginApiService> socialLoginApiServices) {
        this.socialLoginApiServices = socialLoginApiServices;
    }

    public static SocialLoginApiService getSocialLoginApiService(UserType userType) {
        String socialLoginApiServiceBeanName = "";

        if(UserType.KAKAO.equals(userType)) {
            socialLoginApiServiceBeanName = "kakaoLoginApiServiceImpl";
        }
        return socialLoginApiServices.get(socialLoginApiServiceBeanName);
    }

}