package com.example.webshop.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Bean
    MinioClient minioClient(
            @Value("${minio.internal-url}") String internalUrl,
            @Value("${minio.access-key}") String access,
            @Value("${minio.secret-key}") String secret
    ) {
        return MinioClient.builder()
                .endpoint(internalUrl)
                .credentials(access, secret)
                .build();
    }
}


