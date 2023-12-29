package com.moa.rapidus.domain.image.presentation.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ThemeDTO {
    private Long id;
    private String enName;
    private String krName;
}
