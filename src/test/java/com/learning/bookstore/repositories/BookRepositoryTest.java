package com.learning.bookstore.repositories;

import com.learning.bookstore.models.Book;
import com.learning.bookstore.models.Author;
import com.learning.bookstore.models.Category;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private Book book;

    private Author author;

    private Category category;

    @BeforeEach
    void setUp() {
        author = new Author(null, "Test Author", "Author bio");
        category = new Category(null, "Test Category", "Fictional Books");
        book = new Book(null, "Test Book", "ISBN123", 20.0, author, category);
    }

    @Test
    void testSaveBook() {
        Book savedBook = bookRepository.save(book);
        assertNotNull(savedBook);
        assertEquals("Test Book", savedBook.getTitle());
        assertEquals("Test Category", savedBook.getCategory().getName());
        assertEquals("Test Author", savedBook.getAuthor().getName());
    }

    @Test
    void testFindBookById() {
        bookRepository.save(book);
        Book foundBook = bookRepository.findById(book.getId()).orElse(null);
        assertNotNull(foundBook);
        assertEquals("Test Book", foundBook.getTitle());
    }

    @Test
    void testDeleteBook() {
        bookRepository.save(book);
        bookRepository.deleteById(book.getId());
        assertFalse(bookRepository.findById(book.getId()).isPresent());
    }

    @Test
    void testFindAllBooks() {
        bookRepository.save(book);
        Iterable<Book> books = bookRepository.findAll();
        assertNotNull(books);
        assertTrue(books.iterator().hasNext());
    }
}
