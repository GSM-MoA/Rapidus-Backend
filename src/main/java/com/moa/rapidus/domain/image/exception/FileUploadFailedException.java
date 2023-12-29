package com.moa.rapidus.domain.image.exception;

import com.moa.rapidus.global.error.ErrorCode;
import com.moa.rapidus.global.error.GlobalException;
import lombok.Getter;

@Getter
public class FileUploadFailedException extends GlobalException {
    public FileUploadFailedException() {
        super(ErrorCode.FILE_UPLOAD_FAILED);
    }
}
