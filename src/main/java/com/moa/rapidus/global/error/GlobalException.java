package com.moa.rapidus.global.error;

import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException{
    private ErrorCode errorCode;

    public GlobalException(ErrorCode errorCode) {

        super(errorCode.getMessage());

        this.errorCode = errorCode;
    }
}
