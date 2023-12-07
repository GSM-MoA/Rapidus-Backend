package com.moa.rapidus.service;

import com.moa.rapidus.data.entity.Image;
import com.moa.rapidus.data.entity.Theme;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

public interface ImageService {
    Image saveImage(MultipartFile file, String theme, int type) throws IOException;
    Image getImage(Long id);
    Image likeImage(Long id);
    Optional<Theme> getThemeById(Long id);
    Image getTopImageByType(int type);
    List<Image> getImagesByType(int type);
    List<Image> getImagesByTheme(String theme);
    List<Image> getImagesByThemeAndType(String theme, int type);
    Resource loadImageAsResource(String filePath) throws MalformedURLException;


    void deleteImage(Long id);
}
