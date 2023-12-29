package com.moa.rapidus.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNKNOWN_ERROR("알 수 없는 에러입니다.", 500),
    FILE_UPLOAD_FAILED("파일 업로드에 실패했습니다.", 500),
    IMAGE_NOT_FOUND("그림이 존재하지 않습니다.", 404),
    THEME_NOT_FOUND("테마를 찾을 수 없습니다.", 404);

    private final String message;
    private final int status;
}
