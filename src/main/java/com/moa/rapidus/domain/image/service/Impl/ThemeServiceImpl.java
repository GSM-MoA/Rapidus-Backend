package com.moa.rapidus.domain.image.service.Impl;

import com.moa.rapidus.domain.image.domain.entity.Theme;
import com.moa.rapidus.domain.image.domain.repository.ThemeRepository;
import com.moa.rapidus.domain.image.exception.ThemeNotFoundException;
import com.moa.rapidus.domain.image.presentation.data.dto.ThemeDTO;
import com.moa.rapidus.domain.image.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;

    @Override
    public ThemeDTO execute(Long id) {
        Theme themes = themeRepository.findById(id)
                .orElseThrow(ThemeNotFoundException::new);

        return ThemeDTO.builder()
                .id(themes.getId())
                .enName(themes.getEnName())
                .krName(themes.getKrName())
                .build();
    }
}
