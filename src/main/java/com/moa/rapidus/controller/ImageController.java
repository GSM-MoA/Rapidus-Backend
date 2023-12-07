package com.moa.rapidus.controller;

import com.moa.rapidus.data.dto.ImageDTO;
import com.moa.rapidus.data.entity.Image;
import com.moa.rapidus.data.entity.Theme;
import com.moa.rapidus.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
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

    @GetMapping("/search")
    public ResponseEntity<List<Image>> searchImages(@RequestParam("theme") String theme,
                                                    @RequestParam("type") int type) {
        List<Image> images = imageService.getImagesByThemeAndType(theme, type);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @GetMapping("/search/type/{type}")
    public ResponseEntity<List<Image>> searchByType(@PathVariable int type) {
        List<Image> images = imageService.getImagesByType(type);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @GetMapping("/search/theme/{theme}")
    public ResponseEntity<List<Image>> searchByTheme(@PathVariable String theme) {
        List<Image> images = imageService.getImagesByTheme(theme);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Image> getImage(@PathVariable Long id) {
        Image image = imageService.getImage(id);
        if (image != null) {
            return new ResponseEntity<>(image, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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


    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> getImageResource(@PathVariable Long id) {
        Image image = imageService.getImage(id);
        if (image != null) {
            try {
                Resource resource = imageService.loadImageAsResource(image.getFilePath());
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            } catch (MalformedURLException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/most-liked/image/type/{type}")
    public ResponseEntity<?> getMostLikedImage(@PathVariable int type) {
        Image image;
        if (type == 1 || type == 2 || type == 3) {
            image = imageService.getTopImageByType(type);
        } else {
            return new ResponseEntity<>("Invalid type.", HttpStatus.BAD_REQUEST);
        }

        if (image != null) {
            try {
                Resource resource = imageService.loadImageAsResource(image.getFilePath());
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            } catch (MalformedURLException e) {
                return new ResponseEntity<>("Internal Server Error.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Image not found.", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id) {
        try {
            imageService.deleteImage(id);
            return ResponseEntity.ok("Image deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete image");
        }
    }



    private ImageDTO convertToDto(Image image) {
        return ImageDTO.builder()
                .id(image.getId())
                .theme(image.getTheme())
                .type(image.getType())
                .filePath(image.getFilePath())
                .build();
    }
}


