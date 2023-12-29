package com.moa.rapidus.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorMessage {
    private final String message;

    private final int status;

    public ErrorMessage(ErrorCode errorCode){
        this(errorCode.getMessage(), errorCode.getStatus());
    }
}
