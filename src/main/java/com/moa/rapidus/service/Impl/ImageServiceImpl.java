package com.moa.rapidus.service.Impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.moa.rapidus.data.entity.Image;
import com.moa.rapidus.data.entity.Theme;
import com.moa.rapidus.data.repository.ImageRepository;
import com.moa.rapidus.data.repository.ThemeRepository;
import com.moa.rapidus.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.url}")
    private String s3Url;

    private final AmazonS3 amazonS3;

    private final ImageRepository imageRepository;

    private final ThemeRepository themeRepository;

    @Override
    public Image saveImage(MultipartFile file, String theme, int type) throws IOException {
        String fileName = UUID.randomUUID().toString() + ".jpeg";
        String filePath = bucket + "/images/" + fileName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        amazonS3.putObject(bucket, filePath, file.getInputStream(), metadata);


        Image image = Image.builder()
                .filePath(s3Url+filePath)
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
    public Image likeImage(Long id) {
        Image image = imageRepository.findById(id).orElse(null);
        if (image != null) {
            int newLikes = image.getLikes() + 1;
            Image updatedImage = Image.builder()
                    .id(image.getId())
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
}

