package com.example.webshop.service.ipml;

import com.example.webshop.dto.request.CategoryRequest;
import com.example.webshop.mapper.CategoryMapper;
import com.example.webshop.model.Category;
import com.example.webshop.repository.CategoryRepository;
import com.example.webshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryRequest> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toRequestList(categories);
    }

    @Override
    public CategoryRequest getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(categoryMapper::toRequest).orElse(null);
    }

    @Override
    public CategoryRequest createCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapper.toEntity(categoryRequest);
        category = categoryRepository.save(category);
        return categoryMapper.toRequest(category);
    }

    @Override
    public CategoryRequest updateCategory(Long id, CategoryRequest categoryRequest) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setName(categoryRequest.getName());
            category.setDescription(categoryRequest.getDescription());
            category = categoryRepository.save(category);
            return categoryMapper.toRequest(category);
        }
        return null;
    }

    @Override
    public boolean deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
