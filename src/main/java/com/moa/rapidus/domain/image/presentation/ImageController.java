package com.moa.rapidus.domain.image.presentation;

import com.moa.rapidus.domain.image.presentation.data.dto.ImageDTO;
import com.moa.rapidus.domain.image.presentation.data.dto.ThemeDTO;
import com.moa.rapidus.domain.image.presentation.data.request.SaveImageRequestDTO;
import com.moa.rapidus.domain.image.presentation.data.response.ImageResponseDTO;
import com.moa.rapidus.domain.image.presentation.data.response.ListTypeImageResponseDTO;
import com.moa.rapidus.domain.image.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/draw")
@RequiredArgsConstructor

public class ImageController {
    private final SaveImageService saveImageService;

    private final ImageLikeService imageLikeService;

    private final ThemeService themeService;

    private final TypeImagesService typeImagesService;

    private final MostLikedService mostLikedService;

    @PostMapping("/upload")
    public ResponseEntity<ImageDTO> uploadImage(@RequestPart(name = "data") SaveImageRequestDTO saveImageRequestDTO, @RequestPart(name = "file") MultipartFile file){
        saveImageService.execute(saveImageRequestDTO, file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/theme/{id}")
    public ResponseEntity<ThemeDTO> getThemeById(@PathVariable Long id) {
        ThemeDTO themeDTO = themeService.execute(id);
        return new ResponseEntity<>(themeDTO, HttpStatus.OK);
    }

    @GetMapping("/search/type/{type}")
    public ResponseEntity<List<ListTypeImageResponseDTO>> searchByType(@PathVariable int type) {
        ListTypeImageResponseDTO images = typeImagesService.execute(type);
        List<ListTypeImageResponseDTO> responseList = Collections.singletonList(images);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @PatchMapping("/like/{id}")
    public ResponseEntity<Void> likeImage(@PathVariable Long id) {
        imageLikeService.execute(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/most-liked/type/{type}")
    public ResponseEntity<ImageResponseDTO> getMostLikedImageByType(@PathVariable int type) {
        ImageResponseDTO imageResponseDTO = mostLikedService.execute(type);
        return new ResponseEntity<>(imageResponseDTO, HttpStatus.OK);
    }
}


