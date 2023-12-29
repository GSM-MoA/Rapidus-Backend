package com.moa.rapidus.domain.image.presentation.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@AllArgsConstructor
public class SaveImageRequestDTO {

    private MultipartFile file;

    private String theme;

    private int type;
}
