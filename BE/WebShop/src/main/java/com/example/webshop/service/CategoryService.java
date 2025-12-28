package com.example.webshop.service;

import com.example.webshop.dto.request.CategoryRequest;
import java.util.List;


public interface CategoryService {
    List<CategoryRequest> getAllCategories();
    CategoryRequest getCategoryById(Long id);
    CategoryRequest createCategory(CategoryRequest categoryRequest);
    CategoryRequest updateCategory(Long id, CategoryRequest categoryRequest);
    boolean deleteCategory(Long id);
}

