package com.project.viver.service.common.oauth;

import org.springframework.stereotype.Service;

import com.project.viver.common.constraint.UserType;
import com.project.viver.error.ErrorCode;
import com.project.viver.error.exception.BusinessException;

@Service
public class OauthValidator {

    public void validateUserType(String userType) {
        if(!UserType.isUserType(userType)) {
            throw new BusinessException(ErrorCode.INVALID_MEMBER_TYPE);
        }
    }

}
