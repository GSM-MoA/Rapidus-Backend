package com.moa.rapidus.data.repository;

import com.moa.rapidus.data.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByType(int type);
    List<Image> findByTheme(String theme);
    List<Image> findByThemeAndType(String theme, int type);
    Image findTopByTypeOrderByLikesDesc(int type);
}
