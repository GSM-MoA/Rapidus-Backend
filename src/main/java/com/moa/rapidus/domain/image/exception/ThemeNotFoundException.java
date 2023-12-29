package com.moa.rapidus.domain.image.exception;

import com.moa.rapidus.global.error.ErrorCode;
import com.moa.rapidus.global.error.GlobalException;
import lombok.Getter;

@Getter
public class ThemeNotFoundException extends GlobalException {

    public ThemeNotFoundException() {
        super(ErrorCode.THEME_NOT_FOUND);
    }
}
