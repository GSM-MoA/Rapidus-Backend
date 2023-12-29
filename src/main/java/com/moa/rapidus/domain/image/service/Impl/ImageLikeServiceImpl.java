package com.moa.rapidus.domain.image.service.Impl;

import com.moa.rapidus.domain.image.domain.entity.Image;
import com.moa.rapidus.domain.image.domain.repository.ImageRepository;
import com.moa.rapidus.domain.image.exception.ImageNotFoundException;
import com.moa.rapidus.domain.image.service.ImageLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ImageLikeServiceImpl implements ImageLikeService {
    private final ImageRepository imageRepository;

    @Override
    public void execute(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(ImageNotFoundException::new);

        image.like();
    }
}
