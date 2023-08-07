package com.project.viver.error.exception;

import com.project.viver.error.ErrorCode;

public class AuthenticationException extends BusinessException {

    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }

}
