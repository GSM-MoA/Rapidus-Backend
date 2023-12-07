package com.moa.rapidus.service.Impl;

import com.moa.rapidus.data.entity.Image;
import com.moa.rapidus.data.entity.Theme;
import com.moa.rapidus.data.repository.ImageRepository;
import com.moa.rapidus.data.repository.ThemeRepository;
import com.moa.rapidus.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public Image saveImage(MultipartFile file, String theme, int type) throws IOException {
        String fileName = UUID.randomUUID().toString() + ".jpeg";
        String filePath = uploadDir + "/images/" + fileName;

        Path path = Paths.get(filePath);
        Files.write(path, file.getBytes());

        Image image = Image.builder()
                .fileName(fileName)
                .filePath(filePath)
                .likes(0)
                .theme(theme)
                .type(type)
                .build();

        return imageRepository.save(image);
    }

    @Override
    public Optional<Theme> getThemeById(Long id) {
        return themeRepository.findById(id);
    }
    @Override
    public List<Image> getImagesByType(int type) {
        return imageRepository.findByType(type);
    }

    @Override
    public List<Image> getImagesByTheme(String theme) {
        return imageRepository.findByTheme(theme);
    }

    @Override
    public List<Image> getImagesByThemeAndType(String theme, int type) {
        return imageRepository.findByThemeAndType(theme, type);
    }

    @Override
    public Image getImage(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public Image likeImage(Long id) {
        Image image = imageRepository.findById(id).orElse(null);
        if (image != null) {
            int newLikes = image.getLikes() + 1;
            Image updatedImage = Image.builder()
                    .id(image.getId())
                    .fileName(image.getFileName())
                    .filePath(image.getFilePath())
                    .likes(newLikes)
                    .theme(image.getTheme())
                    .type(image.getType())
                    .build();
            return imageRepository.save(updatedImage);
        }
        return null;
    }


    @Override
    public Image getTopImageByType(int type) {
        return imageRepository.findTopByTypeOrderByLikesDesc(type);
    }

    @Override
    public Resource loadImageAsResource(String filePath) throws MalformedURLException {
        Resource resource = new UrlResource("file:" + filePath);

        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("Could not read the file: " + filePath);
        }
    }


    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }
}

