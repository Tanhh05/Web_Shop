package com.example.webshop.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductVariantRequest {
    private String sku;
    private String color;
    private String size;
    private Long originalPrice;
    private Long salePrice;
    private Integer quantity;
}