package com.moa.rapidus.domain.image.domain.repository;

import com.moa.rapidus.domain.image.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByType(int type);

    Optional<Image> findTopByTypeOrderByLikesDesc(int type);
}
