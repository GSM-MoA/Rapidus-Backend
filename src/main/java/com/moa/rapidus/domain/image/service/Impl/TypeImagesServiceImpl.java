package com.moa.rapidus.domain.image.service.Impl;

import com.moa.rapidus.domain.image.domain.entity.Image;
import com.moa.rapidus.domain.image.domain.repository.ImageRepository;
import com.moa.rapidus.domain.image.presentation.data.response.ListTypeImageResponseDTO;
import com.moa.rapidus.domain.image.presentation.data.response.TypeImageResponseDTO;
import com.moa.rapidus.domain.image.service.TypeImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class TypeImagesServiceImpl implements TypeImagesService {

    private final ImageRepository imageRepository;

    public ListTypeImageResponseDTO execute(int type) {

        List<Image> images = imageRepository.findByType(type);

        return ListTypeImageResponseDTO.builder()
                .typeImageList(
                        images.stream()
                                .map(TypeImageResponseDTO::toResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
