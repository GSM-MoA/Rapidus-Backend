package com.moa.rapidus.domain.image.exception;

import com.moa.rapidus.global.error.ErrorCode;
import com.moa.rapidus.global.error.GlobalException;
import lombok.Getter;

@Getter
public class ImageNotFoundException extends GlobalException {

    public ImageNotFoundException() {
        super(ErrorCode.IMAGE_NOT_FOUND);
    }
}
