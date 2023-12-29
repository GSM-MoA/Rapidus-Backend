package com.moa.rapidus.domain.image.exception;

import com.moa.rapidus.global.error.ErrorCode;
import com.moa.rapidus.global.error.GlobalException;

public class FileUploadFailedException extends GlobalException {

    public FileUploadFailedException() {
        super(ErrorCode.FILE_UPLOAD_FAILED);
    }
}
