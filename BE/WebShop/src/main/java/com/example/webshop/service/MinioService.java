package com.example.webshop.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucket;

    @Value("${minio.public-url}")
    private String publicUrl;

    public String upload(MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }

            String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(fileName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            return publicUrl + "/" + bucket + "/" + fileName;

        } catch (Exception e) {
            throw new RuntimeException("Upload image failed", e);
        }
    }
}


