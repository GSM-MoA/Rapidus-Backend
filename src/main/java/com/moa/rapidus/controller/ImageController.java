package com.moa.rapidus.controller;

import com.moa.rapidus.data.dto.ImageDTO;
import com.moa.rapidus.data.entity.Image;
import com.moa.rapidus.data.entity.Theme;
import com.moa.rapidus.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/draw")
@RequiredArgsConstructor

public class ImageController {
    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file,
                                             @RequestParam("theme") String theme,
                                             @RequestParam("type") int type) {
        try {
            Image savedImage = imageService.saveImage(file, theme, type);
            return new ResponseEntity<>(savedImage, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/theme/{id}")
    public ResponseEntity<Theme> getThemeById(@PathVariable Long id) {
        Optional<Theme> theme = imageService.getThemeById(id);

        return theme.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search/type/{type}")
    public ResponseEntity<List<Image>> searchByType(@PathVariable int type) {
        List<Image> images = imageService.getImagesByType(type);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Image> likeImage(@PathVariable Long id) {
        Image image = imageService.likeImage(id);
        if (image != null) {
            return new ResponseEntity<>(image, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/most-liked/type/{type}")
    public ResponseEntity<Image> getMostLikedImageByType(@PathVariable int type) {
        Image image = imageService.getTopImageByType(type);
        if (image != null) {
            return new ResponseEntity<>(image, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


