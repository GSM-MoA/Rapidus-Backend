package com.moa.rapidus.domain.image.presentation.data.response;

import com.moa.rapidus.domain.image.domain.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TypeImageResponseDTO {
    private Long id;
    private String filePath;
    private Integer likes;
    private String theme;
    private Integer type;

    public static TypeImageResponseDTO toResponse(Image image) {
        return TypeImageResponseDTO.builder()
                .id(image.getId())
                .filePath(image.getFilePath())
                .likes(image.getLikes())
                .theme(image.getTheme())
                .type(image.getType())
                .build();
    }
}
