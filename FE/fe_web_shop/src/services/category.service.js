import http from "@/api/http";

// Get all categories
export const getCategories = (page = 0, size = 5) => {
    return http.get("/categories", {
        params: { page, size },
    });
};

// Get a category by its ID
export const getCategoryById = (id) => {
    return http.get(`/categories/${id}`);
};

// Create a new category
export const createCategory = (categoryData) => {
    return http.post("/categories", categoryData);
};

// Update a category by its ID
export const updateCategory = (id, categoryData) => {
    return http.put(`/categories/${id}`, categoryData);
};

// Delete a category by its ID
export const deleteCategory = (id) => {
    return http.delete(`/categories/${id}`);
};
