package com.moa.rapidus.domain.image.service.Impl;

import com.moa.rapidus.domain.image.domain.entity.Image;
import com.moa.rapidus.domain.image.domain.repository.ImageRepository;
import com.moa.rapidus.domain.image.exception.ImageNotFoundException;
import com.moa.rapidus.domain.image.presentation.data.response.ImageResponseDTO;
import com.moa.rapidus.domain.image.service.MostLikedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MostLikedServiceImpl implements MostLikedService {
    private final ImageRepository imageRepository;

    @Override
    public ImageResponseDTO execute(int type) {
        Image image = imageRepository.findTopByTypeOrderByLikesDesc(type)
                .orElseThrow(ImageNotFoundException::new);

        return ImageResponseDTO.builder()
                .id(image.getId())
                .filePath(image.getFilePath())
                .likes(image.getLikes())
                .theme(image.getTheme())
                .type(image.getType())
                .build();
    }
}
