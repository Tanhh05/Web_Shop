package com.example.webshop.controller;

import com.example.webshop.dto.request.CategoryRequest;
import com.example.webshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;  // Tiêm CategoryService để sử dụng các phương thức CRUD

    @GetMapping
    public ResponseEntity<List<CategoryRequest>> getAllCategories() {
        List<CategoryRequest> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Trả về 204 nếu danh sách trống
        } else {
            return new ResponseEntity<>(categories, HttpStatus.OK);  // Trả về 200 và danh sách
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryRequest> getCategoryById(@PathVariable Long id) {
        CategoryRequest categoryRequest = categoryService.getCategoryById(id);
        if (categoryRequest != null) {
            return new ResponseEntity<>(categoryRequest, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Trả về 404 nếu không tìm thấy
        }
    }

    @PostMapping
    public ResponseEntity<CategoryRequest> createCategory(@RequestBody CategoryRequest categoryRequest) {
        CategoryRequest createdCategory = categoryService.createCategory(categoryRequest);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);  // Trả về 201 khi tạo mới thành công
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryRequest> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        CategoryRequest updatedCategory = categoryService.updateCategory(id, categoryRequest);
        if (updatedCategory != null) {
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Trả về 404 nếu không tìm thấy
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (categoryService.deleteCategory(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Trả về 204 khi xóa thành công
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Trả về 404 nếu không tìm thấy
        }
    }
}

