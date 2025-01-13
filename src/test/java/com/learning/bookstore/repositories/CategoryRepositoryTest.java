package com.learning.bookstore.repositories;

import com.learning.bookstore.models.Category;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category(null, "Test Category", "Category description");
    }

    @Test
    void testSaveCategory() {
        Category savedCategory = categoryRepository.save(category);
        assertNotNull(savedCategory);
        assertEquals("Test Category", savedCategory.getName());
    }

    @Test
    void testFindCategoryById() {
        categoryRepository.save(category);
        Category foundCategory = categoryRepository.findById(category.getId()).orElse(null);
        assertNotNull(foundCategory);
        assertEquals("Test Category", foundCategory.getName());
    }

    @Test
    void testDeleteCategory() {
        categoryRepository.save(category);
        categoryRepository.deleteById(category.getId());
        assertFalse(categoryRepository.findById(category.getId()).isPresent());
    }

    @Test
    void testFindAllCategories() {
        categoryRepository.save(category);
        Iterable<Category> categories = categoryRepository.findAll();
        assertNotNull(categories);
        assertTrue(categories.iterator().hasNext());
    }
}
