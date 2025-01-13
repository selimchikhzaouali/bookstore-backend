package com.learning.bookstore.services;

import com.learning.bookstore.models.Category;
import com.learning.bookstore.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.learning.bookstore.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (!existingCategory.isPresent()) {
            throw new ResourceNotFoundException("Category with id " + id + " not found");
        }

        Category category = existingCategory.get();
        category.setName(updatedCategory.getName());
        category.setDescription(updatedCategory.getDescription());
        
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
