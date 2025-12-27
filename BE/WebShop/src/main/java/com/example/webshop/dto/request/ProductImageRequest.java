package com.example.webshop.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductImageRequest {
    private MultipartFile file;
    private String color;
    private Boolean isPrimary;
}