package com.moa.rapidus.domain.image.presentation.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
@AllArgsConstructor
public class ImageDTO {
    private Long id;
    private String filePath;
    private Integer likes;
    private String theme;
    private Integer type;
}
