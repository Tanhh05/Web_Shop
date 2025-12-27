package com.example.webshop.mapper;

import com.example.webshop.dto.response.ProductImageResponse;
import com.example.webshop.dto.response.ProductResponse;
import com.example.webshop.dto.response.ProductVariantResponse;
import com.example.webshop.model.Product;
import com.example.webshop.model.ProductImage;
import com.example.webshop.model.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.name", target = "categoryName")
    ProductResponse toResponse(Product product);

    ProductVariantResponse toVariantResponse(ProductVariant variant);

    ProductImageResponse toImageResponse(ProductImage image);

    List<ProductVariantResponse> toVariantResponseList(List<ProductVariant> variants);

    List<ProductImageResponse> toImageResponseList(List<ProductImage> images);


}
