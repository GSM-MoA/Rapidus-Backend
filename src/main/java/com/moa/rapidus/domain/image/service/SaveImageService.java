package com.moa.rapidus.domain.image.service;

import com.moa.rapidus.domain.image.presentation.data.request.SaveImageRequestDTO;
import org.springframework.web.multipart.MultipartFile;

public interface SaveImageService {
    void execute(SaveImageRequestDTO saveImageRequestDTO, MultipartFile file);
}
