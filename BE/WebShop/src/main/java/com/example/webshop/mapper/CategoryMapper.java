package com.example.webshop.mapper;

import com.example.webshop.dto.request.CategoryRequest;
import com.example.webshop.model.Category;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryRequest toRequest(Category category);

    Category toEntity(CategoryRequest categoryRequest);

    List<CategoryRequest> toRequestList(List<Category> categories);

    List<Category> toEntityList(List<CategoryRequest> categoryRequests);
}
