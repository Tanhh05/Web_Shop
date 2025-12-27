package com.example.webshop.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductImageResponse {
    private Long id;
    private String imageUrl;
    private String color;
    private Boolean isPrimary;
}
