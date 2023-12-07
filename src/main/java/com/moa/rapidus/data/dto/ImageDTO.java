package com.moa.rapidus.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
@AllArgsConstructor
public class ImageDTO {
    private Long id;
    private String theme;
    private int type;
    private String filePath;


}
