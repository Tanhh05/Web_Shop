package com.example.webshop.service.ipml;

import com.example.webshop.dto.request.ProductImageRequest;
import com.example.webshop.dto.request.ProductRequest;
import com.example.webshop.dto.request.ProductVariantRequest;
import com.example.webshop.dto.response.ProductResponse;
import com.example.webshop.mapper.ProductMapper;
import com.example.webshop.model.Product;
import com.example.webshop.model.ProductImage;
import com.example.webshop.model.ProductVariant;
import com.example.webshop.repository.CategoryRepository;
import com.example.webshop.repository.ProductRepository;
import com.example.webshop.service.MinioService;
import com.example.webshop.service.ProductService;
import com.example.webshop.state.ProductStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Qualifier("productMapper")
    private final ProductMapper mapper;
    private final MinioService minioService;

    @Transactional(readOnly = true)
    @Override
    public Page<ProductResponse> getProductPage(int page, int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        Page<Product> productPage = productRepository.findPageWithAll(pageable);

        return productPage.map(mapper::toResponse);
    }


    @Transactional
    @Override
    public ProductResponse addProduct(ProductRequest request) {

        Product product = new Product();
        product.setProductCode(request.getProductCode());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setStatus(ProductStatus.valueOf(request.getStatus()));

        product.setCategory(
                categoryRepository.findById(request.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Category not found"))
        );

        product.setVariants(new HashSet<>());
        product.setImages(new HashSet<>());

        if (request.getVariants() != null) {
            for (ProductVariantRequest v : request.getVariants()) {

                if (v.getOriginalPrice() == null || v.getOriginalPrice() <= 0) {
                    throw new RuntimeException("Original price is required");
                }

                ProductVariant variant = new ProductVariant();
                variant.setSku(v.getSku());
                variant.setColor(v.getColor());
                variant.setSize(v.getSize());
                variant.setOriginalPrice(v.getOriginalPrice());
                variant.setSalePrice(
                        v.getSalePrice() != null ? v.getSalePrice() : v.getOriginalPrice()
                );
                variant.setPrice(
                        v.getSalePrice() != null && v.getSalePrice() > 0
                                ? v.getSalePrice()
                                : v.getOriginalPrice()
                );
                variant.setQuantity(v.getQuantity());
                variant.setProduct(product);

                product.getVariants().add(variant);
            }
        }

        if (request.getImages() != null) {
            for (ProductImageRequest i : request.getImages()) {

                if (i.getFile() == null || i.getFile().isEmpty()) {
                    continue;
                }

                if (i.getColor() == null || i.getColor().isBlank()) {
                    throw new RuntimeException("Image color is required");
                }

                ProductImage image = new ProductImage();
                image.setImageUrl(minioService.upload(i.getFile()));
                image.setColor(i.getColor());
                image.setIsPrimary(Boolean.TRUE.equals(i.getIsPrimary()));
                image.setProduct(product);

                product.getImages().add(image);
            }
        }

        Product saved = productRepository.save(product);
        return mapper.toResponse(saved);
    }

}
