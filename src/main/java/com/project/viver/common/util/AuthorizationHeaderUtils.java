package com.project.viver.common.util;


import org.springframework.util.StringUtils;

import com.project.viver.common.constraint.jwt.GrantType;
import com.project.viver.error.ErrorCode;
import com.project.viver.error.exception.AuthenticationException;

public class AuthorizationHeaderUtils {

    public static void validateAuthorization(String authorizationHeader) {

        // 1. authorizationHeader check
        if(!StringUtils.hasText(authorizationHeader)) {
            throw new AuthenticationException(ErrorCode.NOT_EXISTS_AUTHORIZATION);
        }

        // 2. authorizationHeader Bearer check
        String[] authorizations = authorizationHeader.split(" ");
        if(authorizations.length < 2 || (!GrantType.BEARER.getType().equals(authorizations[0]))) {
            throw new AuthenticationException(ErrorCode.NOT_VALID_BEARER_GRANT_TYPE);
        }
    }

}