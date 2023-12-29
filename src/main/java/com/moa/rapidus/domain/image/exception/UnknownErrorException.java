package com.moa.rapidus.domain.image.exception;

import com.moa.rapidus.global.error.ErrorCode;
import com.moa.rapidus.global.error.GlobalException;
import lombok.Getter;

@Getter
public class UnknownErrorException extends GlobalException {
    public UnknownErrorException() {
            super(ErrorCode.UNKNOWN_ERROR);
        }
}
