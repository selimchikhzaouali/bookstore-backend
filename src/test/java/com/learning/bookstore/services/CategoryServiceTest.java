package com.learning.bookstore.services;

import com.learning.bookstore.models.Category;
import com.learning.bookstore.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        System.out.println("\nInitializing mocks and setting up test environment...");
        MockitoAnnotations.openMocks(this);
        System.out.println("Setup complete.");
    }

    @Test
    void testGetAllCategories() {
        System.out.println("\nStarting test: testGetAllCategories");

        // Arrange
        System.out.println("Setting up mock data...");
        Category category1 = new Category(1L, "Fiction", "Fictional books");
        Category category2 = new Category(2L, "Non-Fiction", "Non-fictional books");
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));

        // Act
        System.out.println("Calling categoryService.getAllCategories()...");
        List<Category> categories = categoryService.getAllCategories();

        // Assert
        System.out.println("Asserting the results...");
        assertNotNull(categories, "The categories list should not be null");
        assertEquals(2, categories.size(), "The categories list should contain 2 items");
        verify(categoryRepository, times(1)).findAll();
        System.out.println("testGetAllCategories passed.");
    }

    @Test
    void testGetCategoryById() {
        System.out.println("\nStarting test: testGetCategoryById");

        // Arrange
        Long categoryId = 1L;
        System.out.println("Setting up mock data for category ID: " + categoryId);
        Category category = new Category(categoryId, "Science", "Books about science");
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        // Act
        System.out.println("Calling categoryService.getCategoryById(" + categoryId + ")...");
        Optional<Category> retrievedCategory = categoryService.getCategoryById(categoryId);

        // Assert
        System.out.println("Asserting the results...");
        assertTrue(retrievedCategory.isPresent(), "The category should be found");
        assertEquals("Science", retrievedCategory.get().getName(), "The category name should be 'Science'");
        verify(categoryRepository, times(1)).findById(categoryId);
        System.out.println("testGetCategoryById passed.");
    }

    @Test
    void testSaveCategory() {
        System.out.println("\nStarting test: testSaveCategory");

        // Arrange
        System.out.println("Creating a new category object...");
        Category category = new Category(null, "History", "Books about history");
        when(categoryRepository.save(category)).thenReturn(category);

        // Act
        System.out.println("Calling categoryService.createCategory()...");
        Category savedCategory = categoryService.createCategory(category);

        // Assert
        System.out.println("Asserting the results...");
        assertNotNull(savedCategory, "The saved category should not be null");
        assertEquals("History", savedCategory.getName(), "The saved category name should be 'History'");
        verify(categoryRepository, times(1)).save(category);
        System.out.println("testSaveCategory passed.");
    }

    @Test
    void testDeleteCategory() {
        System.out.println("\nStarting test: testDeleteCategory");

        // Arrange
        Long categoryId = 1L;
        System.out.println("Preparing to delete category with ID: " + categoryId);
        doNothing().when(categoryRepository).deleteById(categoryId);

        // Act
        System.out.println("Calling categoryService.deleteCategory(" + categoryId + ")...");
        categoryService.deleteCategory(categoryId);

        // Assert
        System.out.println("Verifying that deleteById was called...");
        verify(categoryRepository, times(1)).deleteById(categoryId);
        System.out.println("testDeleteCategory passed.");
    }
}
