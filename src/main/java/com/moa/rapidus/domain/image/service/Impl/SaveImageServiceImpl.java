package com.moa.rapidus.domain.image.service.Impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.moa.rapidus.domain.image.domain.entity.Image;
import com.moa.rapidus.domain.image.domain.repository.ImageRepository;
import com.moa.rapidus.domain.image.exception.FileUploadFailedException;
import com.moa.rapidus.domain.image.presentation.data.request.SaveImageRequestDTO;
import com.moa.rapidus.domain.image.service.SaveImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SaveImageServiceImpl implements SaveImageService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.url}")
    private String s3Url;

    private final AmazonS3 amazonS3;

    private final ImageRepository imageRepository;

    @Override
    public void execute(SaveImageRequestDTO saveImageRequestDTO, MultipartFile file) {
        String fileName = UUID.randomUUID().toString() + ".jpeg";
        String filePath = bucket + "/images/" + fileName;

        ObjectMetadata metadata = new ObjectMetadata();

        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try(InputStream inputStream = file.getInputStream()) {

            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

        } catch(IOException e) {

            throw new FileUploadFailedException();
        }

        Image image = Image.builder()
                .filePath(s3Url+filePath)
                .likes(0)
                .theme(saveImageRequestDTO.getTheme())
                .type(saveImageRequestDTO.getType())
                .build();

        imageRepository.save(image);
    }
}

