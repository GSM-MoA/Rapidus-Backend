package com.moa.rapidus.domain.image.presentation.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ListTypeImageResponseDTO {
    List<TypeImageResponseDTO> typeImageList;
}
