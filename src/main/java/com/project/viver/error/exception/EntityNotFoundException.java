package com.project.viver.error.exception;

import com.project.viver.error.ErrorCode;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

}
