package com.example.webshop.controller;

import com.example.webshop.dto.request.ProductRequest;
import com.example.webshop.dto.response.ProductResponse;
import com.example.webshop.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public Page<ProductResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return productService.getProductPage(page, size);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductResponse addProduct(
            @RequestPart("product") String productJson,
            @RequestPart(value = "images", required = false) List<MultipartFile> files
    ) throws Exception {
        ProductRequest request = objectMapper.readValue(productJson, ProductRequest.class);

        if (request.getImages() != null && request.getImages().isEmpty() && (files == null || files.isEmpty())) {
            throw new ProductException("Images metadata exists but no image files uploaded");
        }

        if (request.getImages() != null && request.getImages().size() != files.size()) {
            throw new ProductException("Number of image metadata must match number of image files");
        }

        if (files != null && !files.isEmpty()) {
            for (int i = 0; i < request.getImages().size(); i++) {
                request.getImages().get(i).setFile(files.get(i));
            }
        }

        return productService.addProduct(request);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public static class ProductException extends RuntimeException {
        public ProductException(String message) {
            super(message);
        }
    }
}
