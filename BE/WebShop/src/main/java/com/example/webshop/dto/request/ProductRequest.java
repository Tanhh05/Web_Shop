package com.example.webshop.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class ProductRequest {
    private String productCode;
    private String name;
    private String description;
    private String status;
    private Long categoryId;
    private List<ProductVariantRequest> variants;
    private List<ProductImageRequest> images;
}
