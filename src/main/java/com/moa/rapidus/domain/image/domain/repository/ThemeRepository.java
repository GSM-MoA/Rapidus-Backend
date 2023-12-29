package com.moa.rapidus.domain.image.domain.repository;

import com.moa.rapidus.domain.image.domain.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
}
