package com.example.webshop.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductVariantResponse {
    private Long id;
    private String sku;
    private String color;
    private String size;
    private Long originalPrice;
    private Long salePrice;
    private Integer quantity;
}
