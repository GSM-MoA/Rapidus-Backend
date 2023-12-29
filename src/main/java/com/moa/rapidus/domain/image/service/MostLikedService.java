package com.moa.rapidus.domain.image.service;

import com.moa.rapidus.domain.image.presentation.data.response.ImageResponseDTO;

public interface MostLikedService {
    ImageResponseDTO execute(int type);
}
