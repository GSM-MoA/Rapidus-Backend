package com.moa.rapidus.domain.image.service;

import com.moa.rapidus.domain.image.presentation.data.response.ListTypeImageResponseDTO;

public interface TypeImagesService {
    ListTypeImageResponseDTO execute(int type);
}
