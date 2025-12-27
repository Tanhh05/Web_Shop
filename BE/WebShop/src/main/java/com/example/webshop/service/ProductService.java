package com.example.webshop.service;

import com.example.webshop.dto.request.ProductRequest;
import com.example.webshop.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {

    @Transactional(readOnly = true)
    Page<ProductResponse> getProductPage(int page, int size);

    @Transactional
    ProductResponse addProduct(ProductRequest request);
}

