package com.example.webshop.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String productCode;
    private String name;
    private String description;
    private String status;
    private String categoryName;
    private List<ProductVariantResponse> variants;
    private List<ProductImageResponse> images;
}
