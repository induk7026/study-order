package com.example.studyorder.common.exception;

import com.example.studyorder.common.response.ErrorCode;

public class EntityNotFoundException extends BaseException {

    public EntityNotFoundException(){
        super(ErrorCode.COMMON_INVALID_PARAMETER);
    }

    public EntityNotFoundException(String message) {
        super(message, ErrorCode.COMMON_INVALID_PARAMETER);
    }
}
