package com.example.webshop.mapper;

import com.example.webshop.dto.response.ProductImageResponse;
import com.example.webshop.dto.response.ProductResponse;
import com.example.webshop.dto.response.ProductVariantResponse;
import com.example.webshop.model.Category;
import com.example.webshop.model.Product;
import com.example.webshop.model.ProductImage;
import com.example.webshop.model.ProductVariant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2025-12-17T02:43:27+0700",
=======
    date = "2025-12-27T12:41:29+0700",
>>>>>>> 5e94152 (tanh fix docker)
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResponse toResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse productResponse = new ProductResponse();

        productResponse.setCategoryName( productCategoryName( product ) );
        productResponse.setId( product.getId() );
        productResponse.setProductCode( product.getProductCode() );
        productResponse.setName( product.getName() );
        productResponse.setDescription( product.getDescription() );
        if ( product.getStatus() != null ) {
            productResponse.setStatus( product.getStatus().name() );
        }
        productResponse.setVariants( productVariantSetToProductVariantResponseList( product.getVariants() ) );
        productResponse.setImages( productImageSetToProductImageResponseList( product.getImages() ) );

        return productResponse;
    }

    @Override
    public ProductVariantResponse toVariantResponse(ProductVariant variant) {
        if ( variant == null ) {
            return null;
        }

        ProductVariantResponse productVariantResponse = new ProductVariantResponse();

        productVariantResponse.setId( variant.getId() );
        productVariantResponse.setSku( variant.getSku() );
        productVariantResponse.setColor( variant.getColor() );
        productVariantResponse.setSize( variant.getSize() );
        productVariantResponse.setOriginalPrice( variant.getOriginalPrice() );
        productVariantResponse.setSalePrice( variant.getSalePrice() );
        productVariantResponse.setQuantity( variant.getQuantity() );

        return productVariantResponse;
    }

    @Override
    public ProductImageResponse toImageResponse(ProductImage image) {
        if ( image == null ) {
            return null;
        }

        ProductImageResponse productImageResponse = new ProductImageResponse();

        productImageResponse.setId( image.getId() );
        productImageResponse.setImageUrl( image.getImageUrl() );
        productImageResponse.setColor( image.getColor() );
        productImageResponse.setIsPrimary( image.getIsPrimary() );

        return productImageResponse;
    }

    @Override
    public List<ProductVariantResponse> toVariantResponseList(List<ProductVariant> variants) {
        if ( variants == null ) {
            return null;
        }

        List<ProductVariantResponse> list = new ArrayList<ProductVariantResponse>( variants.size() );
        for ( ProductVariant productVariant : variants ) {
            list.add( toVariantResponse( productVariant ) );
        }

        return list;
    }

    @Override
    public List<ProductImageResponse> toImageResponseList(List<ProductImage> images) {
        if ( images == null ) {
            return null;
        }

        List<ProductImageResponse> list = new ArrayList<ProductImageResponse>( images.size() );
        for ( ProductImage productImage : images ) {
            list.add( toImageResponse( productImage ) );
        }

        return list;
    }

    private String productCategoryName(Product product) {
        if ( product == null ) {
            return null;
        }
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        String name = category.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected List<ProductVariantResponse> productVariantSetToProductVariantResponseList(Set<ProductVariant> set) {
        if ( set == null ) {
            return null;
        }

        List<ProductVariantResponse> list = new ArrayList<ProductVariantResponse>( set.size() );
        for ( ProductVariant productVariant : set ) {
            list.add( toVariantResponse( productVariant ) );
        }

        return list;
    }

    protected List<ProductImageResponse> productImageSetToProductImageResponseList(Set<ProductImage> set) {
        if ( set == null ) {
            return null;
        }

        List<ProductImageResponse> list = new ArrayList<ProductImageResponse>( set.size() );
        for ( ProductImage productImage : set ) {
            list.add( toImageResponse( productImage ) );
        }

        return list;
    }
}
